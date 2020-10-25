package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import com.assignment.individual.pokedex.entities.PokemonMoveList;
import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.entities.PokemonTypeList;
import com.assignment.individual.pokedex.repositories.PokemonBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pokemon> getAllPokemons() {
        return pokemonRepo.findAll();
    }

    public List<Pokemon> getPokemonByQuery(String name, String type) {
        List<Pokemon> pokemons = new ArrayList<>();
        if (name != null && type != null) {
            pokemons = pokemonRepo.findByNameContainingAndPokemonType(name, type);
            if (pokemons.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon with that name and type.");
            }
        } else if (name != null) {
            pokemons = pokemonRepo.findByNameContaining(name);
            if (pokemons.isEmpty()) {
                return getPokemonsByNameFromPokeAPI(name);
            }
        } else if (type != null) {
            pokemons = pokemonRepo.findByPokemonTypes(type);
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
                    return this.getPokemonByQuery(p.getName(), null).get(0);
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
                    String move = m.getMove().get("name");
                    pokemon.getPokemonMoves().add(move);
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
}