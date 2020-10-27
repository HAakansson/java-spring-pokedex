package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Move;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepo extends MongoRepository<Move, String> {
}
