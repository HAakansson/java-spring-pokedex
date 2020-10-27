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

    List<Pokemon> findByPokemonMoves(String move);

    List<Pokemon> findByWeight(int weight);

    @Query("{$and: [ {'name':/?0/}, {'pokemonTypes':'?1'}, {'pokemonMoves':'?2'}, {'weight':'?3'} ] }")
    List<Pokemon> findByNameContainingAndPokemonTypesAndPokemonMovesAndWeight(String name, String type, String move, int weight);

    @Query("{$and: [ {'name':/?0/}, {'pokemonTypes':'?1'}, {'pokemonMoves':'?2'} ] }")
    List<Pokemon> findByNameContainingAndPokemonTypesAndPokemonMoves(String name, String type, String move);

    @Query("{$and: [ {'pokemonTypes':'?0'}, {'pokemonMoves':'?1'}, {'weight':'?2'} ] }")
    List<Pokemon> findByPokemonTypesAndPokemonMovesAndWeight(String type, String move, int weight);

    @Query("{$and: [ {'name':/?0/}, {'pokemonMoves':'?1'}, {'weight':'?2'} ] }")
    List<Pokemon> findByNameContainingAndPokemonMovesAndWeight(String name, String move, int weight);

    @Query("{$and: [ {'name':/?0/}, {'pokemonTypes':'?1'}, {'weight':'?2'} ] }")
    List<Pokemon> findByNameContainingAndPokemonTypesAndWeight(String name, String type, int weight);

    @Query("{$and: [ {'name':/?0/}, {'pokemonTypes':'?1'} ] }")
    List<Pokemon> findByNameContainingAndPokemonType(String name, String type);

    @Query("{$and: [ {'name':/?0/}, {'pokemonMoves':'?1'} ] }")
    List<Pokemon> findByNameContainingAndPokemonMoves(String name, String move);

    @Query("{$and: [ {'name':/?0/}, {'weight':'?1'} ] }")
    List<Pokemon> findByNameContainingAndWeight(String name, int weight);

    @Query("{$and: [ {'pokemonTypes':'?0'}, {'pokemonMoves':'?1'} ] }")
    List<Pokemon> findByPokemonTypesAndPokemonMoves(String type, String move);

    @Query("{$and: [ {'pokemonTypes':'?0'}, {'weight':'?1'} ] }")
    List<Pokemon> findByPokemonTypesAndWeight(String type, int weight);

    @Query("{$and: [ {'pokemonMoves':'?0'}, {'weight':'?1'} ] }")
    List<Pokemon> findByPokemonMovesAndWeight(String move, int weight);

    Pokemon findByPokemonId(int pokemonId);

    boolean existsByPokemonId(int pokemonId);

    void deleteByPokemonId(int pokemonId);
}