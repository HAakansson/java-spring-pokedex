package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemons(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String type) {
        if (name == null && type == null) {
            var pokemons = pokemonService.getAllPokemons();
            if (pokemons.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Info", "There are no pokemons saved in your database.");
                return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(pokemons);
        }

        var pokemons = pokemonService.getPokemonByQuery(name, type);
        if (pokemons.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Info", "There are no pokemons with that name or type.");
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonByPokedexId(@PathVariable int id){
        Pokemon pokemon = pokemonService.getPokemonByPokedexId(id);
        if(pokemon == null){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Info", "It does not exist a pokemon with that pokedex id.");
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Egentligen ett defaultvärde.
    public ResponseEntity<Pokemon> saveMovie(@RequestBody Pokemon pokemon) {
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
