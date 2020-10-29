package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Generation;
import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moves")
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
}
