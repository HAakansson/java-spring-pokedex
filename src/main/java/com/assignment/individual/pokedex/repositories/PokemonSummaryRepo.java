package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.PokemonSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonSummaryRepo extends MongoRepository<PokemonSummary, String> {
}