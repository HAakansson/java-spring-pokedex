package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends MongoRepository<Type, String> {
    Type findByName(String name);

    Type findByTypeId(int id);

    boolean existsByTypeId(int pokemonId);

    void deleteByTypeId(int typeId);
}
