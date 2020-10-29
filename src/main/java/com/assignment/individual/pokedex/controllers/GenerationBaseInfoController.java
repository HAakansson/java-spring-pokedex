package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.GenerationBaseInfo;
import com.assignment.individual.pokedex.services.GenerationBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allAvailableGenerations")
public class GenerationBaseInfoController {

  @Autowired
  GenerationBaseInfoService generationBaseInfoService;

  @GetMapping
  public ResponseEntity<List<GenerationBaseInfo>> getAllGenerationsAvailable(){
    List<GenerationBaseInfo> generationsAvailable = generationBaseInfoService.getAllGenerationsAvailable();
    return ResponseEntity.ok(generationsAvailable);
  }
}
