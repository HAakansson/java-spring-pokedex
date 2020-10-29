package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.GenerationBaseInfo;
import com.assignment.individual.pokedex.entities.GenerationList;
import com.assignment.individual.pokedex.repositories.GenerationBaseInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GenerationBaseInfoService {

  private final RestTemplate restTemplate;
  public static final String GENERATION_URL = "https://pokeapi.co/api/v2/generation";

  public GenerationBaseInfoService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Autowired
  GenerationBaseInfoRepo generationBaseInfoRepo;

  public List<GenerationBaseInfo> getAllGenerationsAvailable() {
    List<GenerationBaseInfo> generationBaseInfoFromDB = generationBaseInfoRepo.findAll();
    if (generationBaseInfoFromDB.isEmpty()) {
      GenerationList response = restTemplate.getForObject(GENERATION_URL, GenerationList.class);
      List<GenerationBaseInfo> generationList = response.getResults();
      for (var g : generationList) {
        generationBaseInfoRepo.save(g);
      }
      return generationList;
    }
    return generationBaseInfoFromDB;
  }
}
