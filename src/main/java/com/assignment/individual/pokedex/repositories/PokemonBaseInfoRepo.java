package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonBaseInfoRepo extends MongoRepository<PokemonBaseInfo, String> {
}
