package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PokemonRepo extends MongoRepository<Pokemon, String> {

    List<Pokemon> findByNameContaining(String name);
}