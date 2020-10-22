package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.entities.PokemonSummary;
import com.assignment.individual.pokedex.entities.PokemonSummaryList;
import com.assignment.individual.pokedex.repositories.PokemonSummaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonSummaryService {
    private final RestTemplate restTemplate;
    public static final String POKEMONS_URL = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1050";

    @Autowired
    PokemonSummaryRepo pokemonSummaryRepo;

    public PokemonSummaryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<PokemonSummary> fetchAllPokemonsSummary() {
        List<PokemonSummary> pokemonsFromDB = pokemonSummaryRepo.findAll();
        if (pokemonsFromDB.size() == 0) {
            PokemonSummaryList response = restTemplate.getForObject(POKEMONS_URL, PokemonSummaryList.class);
            List<PokemonSummary> pokemonListSummary = response.getResults();
            List<Pokemon> pokemonList;
            for (PokemonSummary p : pokemonListSummary) {
                pokemonSummaryRepo.save(p);
            }
            return pokemonListSummary;
        }
        return pokemonsFromDB;
    }
}
