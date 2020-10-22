package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.entities.PokemonSummary;
import com.assignment.individual.pokedex.services.PokemonService;
import com.assignment.individual.pokedex.services.PokemonSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private PokemonSummaryService pokemonSummaryService;

    @GetMapping
    public ResponseEntity<List<PokemonSummary>> getPokemons(@RequestParam(required = false) String name) {
        if (name == null) {
            var pokemons = pokemonSummaryService.fetchAllPokemonsSummary();
            return ResponseEntity.ok(pokemons);
        }
//        var pokemons = pokemonService.getPokemons(name);
//        return ResponseEntity.ok(pokemons);
        return null;
    }
}
