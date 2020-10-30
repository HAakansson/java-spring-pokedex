package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.*;
import com.assignment.individual.pokedex.repositories.GenerationBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.GenerationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerationService {
  private final RestTemplate restTemplate;

  public GenerationService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Autowired
  GenerationRepo generationRepo;
  @Autowired
  GenerationBaseInfoRepo generationBaseInfoRepo;
  @Autowired
  GenerationBaseInfoService generationBaseInfoService;

  public List<Generation> getAllGenerations() {
    List<Generation> generations;
    generations = generationRepo.findAll();
    if (generations.isEmpty()) {
      generations = getAllGenerationsFromPokeAPI();
    }
    return generations;
  }

  public List<Generation> getGenerationByQuery(String name, String pokemon, String move) {
    List<Generation> generations = new ArrayList<>();

    System.out.println("GENERATION SERVICE");
    System.out.println("name: " + name);
    System.out.println("pokemon: " + pokemon);
    System.out.println("move: " + move);

    if (name != null && pokemon != null && move != null) {
      generations = generationRepo.findByGenerationNameAndPokemonAndPokemonMoves(name, pokemon, move);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation based on that mixture of queries.");
      }

    } else if (name != null && pokemon != null) {
      generations = generationRepo.findByGenerationNameAndPokemon(name, pokemon);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation based on that mixture of queries.");
      }

    } else if (name != null && move != null) {
      generations = generationRepo.findByGenerationNameAndPokemonMoves(name, move);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation based on that mixture of queries.");
      }

    } else if (pokemon != null && move != null) {
      generations = generationRepo.findByPokemonAndPokemonMoves(name, move);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation based on that mixture of queries.");
      }

    } else if (name != null) {
      generations = generationRepo.findByGenerationName(name);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation with that name.");
      }

    } else if (pokemon != null) {
      generations = generationRepo.findByPokemons(pokemon);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation including that pokemon.");
      }

    } else if (move != null) {
      generations = generationRepo.findByPokemonMoves(move);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no generation including that move.");
      }

    }
    return generations;
  }

  public Generation getPokemonByGenerationId(int id) {
    var generation = generationRepo.findByGenerationId(id);
    if (generation == null) {
      List<GenerationBaseInfo> results = generationBaseInfoRepo.findAll();
      for (var g : results) {
        if (g.getUrl().split("/")[6].equals(Integer.toString(id))) {
          // Returns a list, we now this list only contains ONE pokemon, that is why we pick the first (and only) object.
          return this.getGenerationByQuery(g.getName(), null, null).get(0);
        }
      }
    }
    return generation;
  }

  public Generation save(Generation generation) {
    return generationRepo.save(generation);
  }

  public void update(int id, Generation generation) {
    if (!generationRepo.existsByGenerationId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any generation with that generation id");
    }
    generation.setGenerationId(id);
    generationRepo.save(generation);
  }

  public void delete(int id) {
    if (!generationRepo.existsByGenerationId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any generation with that generation id");
    }
    generationRepo.deleteByGenerationId(id);
  }

  public List<Generation> getAllGenerationsFromPokeAPI() {
    List<Generation> generations = new ArrayList<>();
    List<GenerationBaseInfo> generationsBaseInfo = generationBaseInfoService.getAllGenerationsAvailable();
    for (var g : generationsBaseInfo) {
      Generation gen = restTemplate.getForObject(g.getUrl(), Generation.class);
      GenerationMainRegion region = restTemplate.getForObject(g.getUrl(), GenerationMainRegion.class);
      GenerationPokemonList pokemons = restTemplate.getForObject(g.getUrl(), GenerationPokemonList.class);
      GenerationPokemonMovesList moves = restTemplate.getForObject(g.getUrl(), GenerationPokemonMovesList.class);
      gen.setUrl(g.getUrl());
      gen.setGenerationName(region.getMain_region().get("name"));
      for (var p : pokemons.getPokemon_species()) {
        gen.getPokemons().add(p.getName());
      }
      for (var m : moves.getMoves()) {
        gen.getPokemonMoves().add(m.getName());
      }
      gen = generationRepo.save(gen);
      generations.add(gen);
    }
    return generations;
  }
}
