package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.GenerationBaseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenerationBaseInfoRepo extends MongoRepository<GenerationBaseInfo, String> {
}
