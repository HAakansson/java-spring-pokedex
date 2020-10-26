package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.Type;
import com.assignment.individual.pokedex.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> getTypes(){
        var types = typeService.getAllTypes();
        return ResponseEntity.ok(types);
    }
}
