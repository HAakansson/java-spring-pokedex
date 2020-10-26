package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemons(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String type,
                                                     @RequestParam(required = false) String move,
                                                     @RequestParam(required = false) String weight) {
        if (name == null && type == null && move == null && weight == null) {
            var pokemons = pokemonService.getAllPokemons();
            if (pokemons.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon in your database.");
            }
            return ResponseEntity.ok(pokemons);
        }

        var pokemons = pokemonService.getPokemonByQuery(name, type, move, weight);
        if (pokemons.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any pokemon with that combination of queries.");
        }
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonByPokedexId(@PathVariable int id){
        Pokemon pokemon = pokemonService.getPokemonByPokedexId(id);
        if(pokemon == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "It does not exist a pokemon with that pokedex id.");
        }
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Egentligen ett defaultvärde.
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        var pokemonToSave = pokemonService.save(pokemon);
        var uri = URI.create("/api/v1/pokemons/" + pokemonToSave.getPokemonId());
        return ResponseEntity.created(uri).body(pokemonToSave);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable int id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable int id) {
        pokemonService.delete(id);
    }
}
