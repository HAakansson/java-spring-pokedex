package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.Generation;
import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.repositories.GenerationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerationService {

  @Autowired
  GenerationRepo generationRepo;

  public List<Generation> getAllGenerations() {
    return generationRepo.findAll();
  }

  public List<Generation> getGenerationByQuery(String name, String pokemon, String move) {
    List<Generation> generations = new ArrayList<>();

    System.out.println("GENERATION SERVICE");
    System.out.println("name: " + name);
    System.out.println("pokemon: " + pokemon);
    System.out.println("move: " + move);

    if (name != null && pokemon != null && move != null) {
      generations = generationRepo.findByNameContainingAndPokemonTypesAndPokemonMoves(name, pokemon, move);
      if (generations.isEmpty()) {
        generations = getPokemonsByNameFromPokeAPI(name);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getPokemonTypes().contains(pokemon) && p.getPokemonMoves().contains(move))
              .collect(Collectors.toList());
        }
      }

    } else if (name != null && pokemon != null) {
      generations = generationRepo.findByNameContainingAndPokemonType(name, pokemon);
      if (generations.isEmpty()) {
        generations = getPokemonsByNameFromPokeAPI(name);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getPokemonTypes().contains(pokemon))
              .collect(Collectors.toList());
        }
      }

    } else if (name != null && move != null) {
      generations = generationRepo.findByNameContainingAndPokemonMoves(name, move);
      if (generations.isEmpty()) {
        generations = getPokemonsByNameFromPokeAPI(name);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getPokemonMoves().contains(move))
              .collect(Collectors.toList());
        }
      }

    } else if (name != null && weight != null) {
      generations = generationRepo.findByNameContainingAndWeight(name, Integer.parseInt(weight));
      if (generations.isEmpty()) {
        generations = getPokemonsByNameFromPokeAPI(name);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getWeight() == Integer.parseInt(weight))
              .collect(Collectors.toList());
        }
      }

    } else if (pokemon != null && move != null) {
      generations = generationRepo.findByPokemonTypesAndPokemonMoves(pokemon, move);
      if (generations.isEmpty()) {
        generations = getPokemonsByType(pokemon);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getPokemonMoves().contains(move))
              .collect(Collectors.toList());
        }
      }

    } else if (pokemon != null && weight != null) {
      generations = generationRepo.findByPokemonTypesAndWeight(pokemon, Integer.parseInt(weight));
      if (generations.isEmpty()) {
        generations = getPokemonsByType(pokemon);
        if (!generations.isEmpty()) {
          generations = generations.stream()
              .filter(g -> g.getWeight() == Integer.parseInt(weight))
              .collect(Collectors.toList());
        }
      }

    } else if (move != null && weight != null) {
      generations = generationRepo.findByPokemonMovesAndWeight(move, Integer.parseInt(weight));
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pokemon in the database with that combination of queries, try adding a name to the search instead.");
      }

    } else if (name != null) {
      generations = generationRepo.findByNameContaining(name);
      if (generations.isEmpty()) {
        generations = getPokemonsByNameFromPokeAPI(name);
      }

    } else if (pokemon != null) {
      generations = generationRepo.findByPokemonTypes(pokemon);
      if (generations.isEmpty()) {
        generations = getPokemonsByType(pokemon);
      }

    } else if (move != null) {
      generations = generationRepo.findByPokemonMoves(move);
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pokemon in the database with that combination of queries, try adding a name to the search instead.");
      }

    } else if (weight != null) {
      generations = generationRepo.findByWeight(Integer.parseInt(weight));
      if (generations.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pokemon in the database with that combination of queries, try adding a name to the search instead.");
      }

    }

    return generations;
  }
}
