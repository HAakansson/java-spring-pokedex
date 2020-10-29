package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepo extends MongoRepository<Type, String> {

  Type findByTypeId(int typeId);

  boolean existsByTypeId(int typeId);

  void deleteByTypeId(int typeId);

  Type findByName(String name);

  @Query("{'moveList':/?0/}")
  List<Type> findByMoveListContaining(String move);

  @Query("{'pokemonList':/?0/}")
  List<Type> findByPokemonListContaining(String pokemon);

  List<Type> findByDoubleDamageTo(String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'moveList':/?1/}, {'pokemonList':/?2/}, {'doubleDamageTo':'?3'} ] }")
  List<Type> findByNameAndMoveAndPokemonAndDoubleDamageTo(String name, String move, String pokemon, String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'moveList':/?1/}, {'pokemonList':/?2/} ] }")
  List<Type> findByNameAndMoveAndPokemon(String name, String move, String pokemon);

  @Query("{$and: [ {'moveList':/?0/}, {'pokemonList':/?1/}, {'doubleDamageTo':'?2'} ] }")
  List<Type> findByMoveAndPokemonAndDoubleDamageTo(String move, String pokemon, String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'pokemonList':/?1/}, {'doubleDamageTo':'?2'} ] }")
  List<Type> findByNameAndPokemonAndDoubleDamageTo(String name, String pokemon, String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'moveList':/?1/}, {'doubleDamageTo':'?2'} ] }")
  List<Type> findByNameAndMoveAndDoubleDamageTo(String name, String move, String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'moveList':'?1'} ] }")
  List<Type> findByNameAndMove(String name, String move);

  @Query("{$and: [ {'name':'?0'}, {'doubleDamageTo':'?1'} ] }")
  List<Type> findByNameAndDoubleDamageTo(String name, String doubleDamageTo);

  @Query("{$and: [ {'name':'?0'}, {'pokemonList':/?1/} ] }")
  List<Type> findByNameAndPokemon(String name, String pokemon);

  @Query("{$and: [ {'moveList':/?0/}, {'doubleDamageTo':'?1'} ] }")
  List<Type> findByMoveAndDoubleDamageTo(String move, String doubleDamageTo);

  @Query("{$and: [ {'moveList':/?0/}, {'pokemonList':/?1/} ] }")
  List<Type> findByMoveAndPokemon(String move, String pokemon);

  @Query("{$and: [ {'pokemonList':/?0/}, {'doubleDamageTo':'?1'} ] }")
  List<Type> findByPokemonAndDoubleDamageTo(String pokemon, String doubleDamageTo);
}
