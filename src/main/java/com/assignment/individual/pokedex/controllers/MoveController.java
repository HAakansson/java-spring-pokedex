package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Move;
import com.assignment.individual.pokedex.services.MoveService;
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

}
