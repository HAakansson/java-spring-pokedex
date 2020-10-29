package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Generation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepo extends MongoRepository<Generation, String> {

  @Query("{'generationName':/?0/}")
  List<Generation> findByGenerationNameContaining(String name);

  @Query("{'pokemons':/?0/}")
  List<Generation> findByPokemonsContaining(String pokemon);

  @Query("{'pokemonsMoves':/?0/}")
  List<Generation> findByPokemonMovesContaining(String move);

  @Query("{$and: [ {'name':/?0/}, {'pokemons':/?1/}, {'pokemonMoves':/?2/} ] }")
  List<Generation> findByNameContainingAndPokemonTypesAndPokemonMoves(String name, String pokemon, String move);

  @Query("{$and: [ {'name':/?0/}, {'pokemons':/?1/} ] }")
  List<Generation> findByNameContainingAndPokemonType(String name, String pokemon);

  @Query("{$and: [ {'name':/?0/}, {'pokemonMoves':/?1/} ] }")
  List<Generation> findByNameContainingAndPokemonMoves(String name, String move);

  @Query("{$and: [ {'pokemons':/?0/}, {'pokemonMoves':/?1/} ] }")
  List<Generation> findByPokemonTypesAndPokemonMoves(String pokemon, String move);

  Generation findByPokemonId(int generationId);

  boolean existsByPokemonId(int generationId);

  void deleteByPokemonId(int generationId);
}
