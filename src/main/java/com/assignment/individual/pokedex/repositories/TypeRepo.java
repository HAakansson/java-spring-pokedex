package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends MongoRepository<Type, String> {
    Type findByName(String name);

    boolean existsByTypeId(int pokemonId);

    boolean existsByName(String name);

    void deleteByTypeId(int typeId);
}
