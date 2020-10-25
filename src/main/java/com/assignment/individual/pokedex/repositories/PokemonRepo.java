package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepo extends MongoRepository<Pokemon, String> {

    List<Pokemon> findByNameContaining(String name);
    List<Pokemon> findByPokemonTypes(String type);
    @Query("{$and: [ {'name':/?0/}, {'pokemonTypes' : '?1'} ] }")
    List<Pokemon> findByNameContainingAndPokemonType(String name, String type);
    Pokemon findByPokemonId(int pokemonId);
    boolean existsByPokemonId(int pokemonId);
    void deleteByPokemonId(int pokemonId);
}