package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Pokemon;
import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonBaseInfoRepo extends MongoRepository<PokemonBaseInfo, String> {
    List<PokemonBaseInfo> findByNameContaining(String name);
}
