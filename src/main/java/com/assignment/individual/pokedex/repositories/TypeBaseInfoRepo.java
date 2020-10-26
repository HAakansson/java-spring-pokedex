package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.TypeBaseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeBaseInfoRepo extends MongoRepository<TypeBaseInfo, String> {
    TypeBaseInfo findByName(String name);

    boolean existsByName(String name);
}
