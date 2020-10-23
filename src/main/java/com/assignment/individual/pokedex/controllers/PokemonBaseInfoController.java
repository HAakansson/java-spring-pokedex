package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import com.assignment.individual.pokedex.services.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allAvailablePokemons")
public class PokemonBaseInfoController {

    @Autowired
    PokemonBaseInfoService pokemonBaseInfoService;

    @GetMapping
    public ResponseEntity<List<PokemonBaseInfo>> getAllPokemonsAvailable(){
        List<PokemonBaseInfo> pokemonsAvailable = pokemonBaseInfoService.getAllPokemonsAvailable();
        return ResponseEntity.ok(pokemonsAvailable);
    }
}
