package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Type;
import com.assignment.individual.pokedex.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

  @Autowired
  TypeService typeService;

  @GetMapping
  public ResponseEntity<List<Type>> getTypes(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String move,
                                             @RequestParam(required = false) String pokemon,
                                             @RequestParam(required = false) String doubleDamageTo) {
    if (name == null && move == null && pokemon == null && doubleDamageTo == null) {
      var types = typeService.getAllTypes();
      return ResponseEntity.ok(types);
    }

    var types = typeService.getTypeByQuery(name, move, pokemon, doubleDamageTo);
    if (types.isEmpty()) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Info", "There are no types with that combination of queries.");
      return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(types);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Type> getTypeByTypeId(@PathVariable String id) {
    return ResponseEntity.ok(typeService.getTypeByTypeId(Integer.parseInt(id)));
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Egentligen ett defaultvärde.
  public ResponseEntity<Type> saveType(@RequestBody Type type) {
    var typeToSave = typeService.save(type);
    var uri = URI.create("/api/v1/type/" + typeToSave.getTypeId());
    return ResponseEntity.created(uri).body(typeToSave);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updatePokemon(@PathVariable int id, @RequestBody Type type) {
    typeService.update(id, type);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePokemon(@PathVariable int id) {
    typeService.delete(id);
  }


}
