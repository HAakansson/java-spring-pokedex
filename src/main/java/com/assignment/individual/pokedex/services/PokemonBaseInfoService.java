package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import com.assignment.individual.pokedex.entities.PokemonList;
import com.assignment.individual.pokedex.repositories.PokemonBaseInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonBaseInfoService {
    private final RestTemplate restTemplate;
    public static final String POKEMONS_URL = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1050";

    public PokemonBaseInfoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    PokemonBaseInfoRepo pokemonBaseInfoRepo;

    public List<PokemonBaseInfo> getAllPokemonsAvailable() {
        List<PokemonBaseInfo> pokemonsBaseInfoFromDB = pokemonBaseInfoRepo.findAll();
        if (pokemonsBaseInfoFromDB.size() == 0) {
            PokemonList response = restTemplate.getForObject(POKEMONS_URL, PokemonList.class);
            List<PokemonBaseInfo> pokemonList = response.getResults();
            for (var p : pokemonList) {
                pokemonBaseInfoRepo.save(p);
            }
            System.out.println("Requested from PokeAPI.");
            return pokemonList;
        }
        System.out.println("Requested from Mongo.");
        return pokemonsBaseInfoFromDB;
    }
}
