package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.*;
import com.assignment.individual.pokedex.repositories.PokemonBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    PokemonRepo pokemonRepo;
    @Autowired
    PokemonBaseInfoRepo pokemonBaseInfoRepo;
    @Autowired
    TypeService typeService;


    public List<Pokemon> getAllPokemons() {
        return pokemonRepo.findAll();
    }

    public List<Pokemon> getPokemonByQuery(String name, String type, String move, String weight) {
        List<Pokemon> pokemons = new ArrayList<>();

        if (name != null && type != null && move != null && weight != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonTypesAndPokemonMovesAndWeight(name, type, move, weight);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByNameFromPokeAPI(name);
                if (!pokemons.isEmpty()) {
                    pokemons = pokemons.stream()
                            .filter(p -> p.getPokemonTypes().contains(type) && p.getPokemonMoves().contains(move) && p.getWeight() == Integer.parseInt(weight))
                            .collect(Collectors.toList());
                }
            }

        } else if (name != null && type != null && move != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonTypesAndPokemonMoves(name, type, move);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByNameFromPokeAPI(name);
                if (!pokemons.isEmpty()) {
                    pokemons = pokemons.stream()
                            .filter(p -> p.getPokemonTypes().contains(type) && p.getPokemonMoves().contains(move))
                            .collect(Collectors.toList());
                }
            }

        } else if (type != null && move != null && weight != null) {
            pokemons = pokemonRepo.findByPokemonTypesAndPokemonMovesAndWeight(type, move, weight);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByType(type);
                if (!pokemons.isEmpty()) {
                    pokemons = pokemons.stream()
                            .filter(p -> p.getPokemonMoves().contains(move) && p.getWeight() == Integer.parseInt(weight))
                            .collect(Collectors.toList());
                }
            }

        } else if (name != null && move != null && weight != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonMovesAndWeight(name, move, weight);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByNameFromPokeAPI(name);
                if (!pokemons.isEmpty()) {
                    pokemons = pokemons.stream()
                            .filter(p -> p.getPokemonMoves().contains(move) && p.getWeight() == (Integer.parseInt(weight)))
                            .collect(Collectors.toList());
                }
            }

        } else if (name != null && type != null && weight != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonTypesAndWeight(name, type, weight);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByNameFromPokeAPI(name);
                if (!pokemons.isEmpty()) {
                    pokemons = pokemons.stream()
                            .filter(p -> p.getPokemonTypes().contains(type) && p.getWeight() == (Integer.parseInt(weight)))
                            .collect(Collectors.toList());
                }
            }

        } else if (name != null && type != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonType(name, type);
            if (pokemons.isEmpty()) {
                pokemons = getPokemonsByNameFromPokeAPI(name);
                if (!pokemons.isEmpty()) {
                    return pokemons.stream()
                            .filter(p -> p.getPokemonTypes().contains(type))
                            .collect(Collectors.toList());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon with that name and type.");
                }
            }

        } else if (name != null) {
            pokemons = pokemonRepo.findByNameContaining(name);
            if (pokemons.isEmpty()) {
                return getPokemonsByNameFromPokeAPI(name);
            }

        } else if (type != null) {
            pokemons = pokemonRepo.findByPokemonTypes(type);

        } else if (move != null) {
            pokemons = pokemonRepo.findByPokemonMoves(move);

        } else if (weight != null) {
            pokemons = pokemonRepo.findByWeight(weight);
        }

        return pokemons;
    }

    public Pokemon getPokemonByPokedexId(int id) {
        var pokemon = pokemonRepo.findByPokemonId(id);
        if (pokemon == null) {
            String urlToPokemon;
            List<PokemonBaseInfo> results = pokemonBaseInfoRepo.findAll();
            for (var p : results) {
                if (p.getUrl().split("/")[6].equals(Integer.toString(id))) {
                    // Returns a list, we now this list only contains ONE pokemon, that is why we pick the first (and only) object.
                    return this.getPokemonByQuery(p.getName(), null, null, null).get(0);
                }
            }
        }
        return pokemon;
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonRepo.save(pokemon);
    }

    public void update(int id, Pokemon pokemon) {
        if (!pokemonRepo.existsByPokemonId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon with that pokedex id");
        }
        pokemon.setPokemonId(id);
        pokemonRepo.save(pokemon);
    }

    public void delete(int id) {
        if (!pokemonRepo.existsByPokemonId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon with that pokemon id");
        }
        pokemonRepo.deleteByPokemonId(id);
    }

    public List<Pokemon> getPokemonsByNameFromPokeAPI(String name) {
        List<Pokemon> pokemons = new ArrayList<>();
        var listOfPokemonBaseInfo = pokemonBaseInfoRepo.findByNameContaining(name);
        if (!listOfPokemonBaseInfo.isEmpty()) {
            for (var p : listOfPokemonBaseInfo) {
                Pokemon pokemon = restTemplate.getForObject(p.getUrl(), Pokemon.class);
                PokemonMoveList pokemonMoveList = restTemplate.getForObject(p.getUrl(), PokemonMoveList.class);
                PokemonTypeList pokemonTypeList = restTemplate.getForObject(p.getUrl(), PokemonTypeList.class);
                for (var m : pokemonMoveList.getMoves()) {
                    String moveToAdd = m.getMove().get("name");
                    pokemon.getPokemonMoves().add(moveToAdd);
                }
                for (var t : pokemonTypeList.getTypes()) {
                    String typeToAdd = t.getType().get("name");
                    pokemon.getPokemonTypes().add(typeToAdd);
                }
                pokemon.setUrl(p.getUrl());
                pokemon = pokemonRepo.save(pokemon);
                pokemons.add(pokemon);
            }
            return pokemons;
        }
        return pokemons;
    }

    public List<Pokemon> getPokemonsByType(String type) {
        List<Pokemon> pokemons = new ArrayList<>();
        Type typeTofind = typeService.getTypeByName(type);
        for (var p : typeTofind.getPokemonList()) {
            Pokemon pokemon = this.getPokemonByQuery(p, null, null, null).get(0);
            pokemons.add(pokemon);
        }
        return pokemons;
    }
}