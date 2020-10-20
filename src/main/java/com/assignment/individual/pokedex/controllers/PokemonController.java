package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.PokemonSummary;
import com.assignment.individual.pokedex.services.PokemonService;
import com.assignment.individual.pokedex.services.PokemonSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonSummaryService pokemonSummaryService;

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<PokemonSummary> getPokemons(){
        return pokemonSummaryService.fetchAllPokemonsSummary();
    }
}
