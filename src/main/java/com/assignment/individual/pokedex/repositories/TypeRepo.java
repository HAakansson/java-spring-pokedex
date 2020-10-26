package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends MongoRepository<Type, String> {
}
