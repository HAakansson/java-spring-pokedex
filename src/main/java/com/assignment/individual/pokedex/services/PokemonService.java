package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import com.assignment.individual.pokedex.entities.PokemonMoveList;
import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.entities.PokemonTypeList;
import com.assignment.individual.pokedex.repositories.PokemonBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<Pokemon> getAllPokemons(){
        return pokemonRepo.findAll();
    }

    public List<Pokemon> getPokemonByQuery(String name){
        var pokemons = pokemonRepo.findByNameContaining(name);

        if(pokemons.isEmpty()){
            var listOfPokemonBaseInfo = pokemonBaseInfoRepo.findByNameContaining(name);
            if(!listOfPokemonBaseInfo.isEmpty()) {
                for (var p : listOfPokemonBaseInfo) {
                    Pokemon pokemon = restTemplate.getForObject(p.getUrl(), Pokemon.class);
                    PokemonMoveList pokemonMoveList = restTemplate.getForObject(p.getUrl(), PokemonMoveList.class);
                    PokemonTypeList pokemonTypeList = restTemplate.getForObject(p.getUrl(), PokemonTypeList.class);
                    pokemon.setMoves(pokemonMoveList.getMoves());
                    pokemon.setTypes(pokemonTypeList.getTypes());
                    pokemon.setUrl(p.getUrl());
                    pokemon = pokemonRepo.save(pokemon);
                    pokemons.add(pokemon);
                }
                return pokemons;
            }
            return new ArrayList<>();
        }
        return pokemons;
    }

    public Pokemon getPokemonByPokedexId(int id){
        var pokemon = pokemonRepo.findByPokemonId(id);
        if(pokemon == null){
            String urlToPokemon;
            List<PokemonBaseInfo> results = pokemonBaseInfoRepo.findAll();
            for(var p : results){
                if(p.getUrl().split("/")[6].equals(Integer.toString(id))){
                   return this.getPokemonByQuery(p.getName()).get(0);
                }
            }
        }
        return pokemon;
    }
}