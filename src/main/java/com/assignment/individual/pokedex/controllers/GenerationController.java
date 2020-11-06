package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Generation;
import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/generations")
public class GenerationController {

  @Autowired
  GenerationService generationService;

  @GetMapping
  public ResponseEntity<List<Generation>> getGenerations(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String pokemon,
                                                         @RequestParam(required = false) String move) {
    if (name == null && pokemon == null && move == null) {
      var generations = generationService.getAllGenerations();
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any generations in your database.");
      }
      return ResponseEntity.ok(generations);
    }

    var generations = generationService.getGenerationByQuery(name, pokemon, move);
    if (generations.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any generation with that combination of queries.");
    }
    return ResponseEntity.ok(generations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Generation> getPokemonByPokedexId(@PathVariable int id){
    Generation generation = generationService.getPokemonByGenerationId(id);
    if(generation == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "It does not exist a generation with that generation id.");
    }
    return ResponseEntity.ok(generation);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Egentligen ett defaultvärde.
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Generation> savePokemon(@RequestBody Generation generation) {
    var generationToSave = generationService.save(generation);
    var uri = URI.create("/api/v1/pokemons/" + generationToSave.getGenerationId());
    return ResponseEntity.created(uri).body(generationToSave);
  }

  @PutMapping("/{id}")
  @Secured("ROLE_ADMIN")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateGeneration(@PathVariable int id, @RequestBody Generation generation) {
    generationService.update(id, generation);
  }

  @DeleteMapping("/{id}")
  @Secured("ROLE_ADMIN")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGeneration(@PathVariable int id) {
    generationService.delete(id);
  }
}
