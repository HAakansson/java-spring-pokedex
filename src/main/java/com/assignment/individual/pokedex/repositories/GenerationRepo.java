package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Generation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepo extends MongoRepository<Generation, String> {

  @Query("{'generationName':/?0/}")
  List<Generation> findByGenerationName(String name);

  @Query("{'pokemons':/?0/}")
  List<Generation> findByPokemons(String pokemon);

  @Query("{'pokemonMoves':/?0/}")
  List<Generation> findByPokemonMoves(String move);

  @Query("{$and: [ {'generationName':/?0/}, {'pokemons':/?1/}, {'pokemonMoves':/?2/} ] }")
  List<Generation> findByGenerationNameAndPokemonAndPokemonMoves(String name, String pokemon, String move);

  @Query("{$and: [ {'generationName':/?0/}, {'pokemons':/?1/} ] }")
  List<Generation> findByGenerationNameAndPokemon(String name, String pokemon);

  @Query("{$and: [ {'generationName':/?0/}, {'pokemonMoves':/?1/} ] }")
  List<Generation> findByGenerationNameAndPokemonMoves(String name, String move);

  @Query("{$and: [ {'pokemons':/?0/}, {'pokemonMoves':/?1/} ] }")
  List<Generation> findByPokemonAndPokemonMoves(String pokemon, String move);

  Generation findByGenerationId(int generationId);

  boolean existsByGenerationId(int generationId);

  void deleteByGenerationId(int generationId);
}
