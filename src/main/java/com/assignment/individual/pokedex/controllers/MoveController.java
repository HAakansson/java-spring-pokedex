package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Move;
import com.assignment.individual.pokedex.services.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/moves")
public class MoveController {

  @Autowired
  MoveService moveService;


  @GetMapping
  public ResponseEntity<List<Move>> getMoves(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String power,
                                             @RequestParam(required = false) String damageClass,
                                             @RequestParam(required = false) String type) {
    if (name == null && power == null && damageClass == null && type == null) {
      var moves = moveService.getAllMoves();
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any moves in your database.");
      }
      return ResponseEntity.ok(moves);
    }

    var moves = moveService.getMovesByQuery(name, power, damageClass, type);
    if (moves.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any moves with that combination of queries.");
    }
    return ResponseEntity.ok(moves);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Move> getMoveByMoveId(@PathVariable String id) {
    return ResponseEntity.ok(moveService.getMoveByMoveId(Integer.parseInt(id)));
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Egentligen ett defaultvärde.
  public ResponseEntity<Move> savePokemon(@RequestBody Move move) {
    var moveToSave = moveService.save(move);
    var uri = URI.create("/api/v1/moves/" + moveToSave.getMoveId());
    return ResponseEntity.created(uri).body(moveToSave);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updatePokemon(@PathVariable int id, @RequestBody Move move) {
    moveService.update(id, move);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePokemon(@PathVariable int id) {
    moveService.delete(id);
  }

}
