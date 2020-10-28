package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Move;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepo extends MongoRepository<Move, String> {
  List<Move> findByNameContaining(String name);

  List<Move> findByPower(int power);

  List<Move> findByAttackClass(String damageClass);

  List<Move> findByAttackType(String type);

  boolean existsByMoveId(int id);

  void deleteByMoveId(int id);

  Move findByMoveId(int id);

  @Query("{$and: [ {'name':/?0/}, {'power':'?1'}, {'attackClass':'?2'}, {'attackType':'?3'} ] }")
  List<Move> findByNameContainingAndPowerAndAttackClassAndType(String name, int power, String damageClass, String type);

  @Query("{$and: [ {'name':/?0/}, {'power':'?1'}, {'attackClass':'?2'} ] }")
  List<Move> findByNameContainingAndPowerAndAttackClass(String name, int power, String damageClass);

  @Query("{$and: [ {'power':'?0'}, {'attackClass':'?1'}, {'attackType':'?2'} ] }")
  List<Move> findByPowerAndAttackClassAndAttackType(int power, String damageClass, String type);

  @Query("{$and: [ {'name':/?0/}, {'attackClass':'?1'}, {'attackType':'?2'} ] }")
  List<Move> findByNameContainingAndAttackClassAndAttackType(String name, String damageClass, String type);

  @Query("{$and: [ {'name':/?0/}, {'power':'?1'}, {'attackType':'?2'} ] }")
  List<Move> findByNameContainingAndPowerAndAttackType(String name, int power, String type);

  @Query("{$and: [ {'name':/?0/}, {'power':'?1'} ] }")
  List<Move> findByNameContainingAndPower(String name, int power);

  @Query("{$and: [ {'name':/?0/}, {'attackType':'?1'} ] }")
  List<Move> findByNameContainingAndAttackType(String name, String type);

  @Query("{$and: [ {'name':/?0/}, {'attackClass':'?1'} ] }")
  List<Move> findByNameContainingAndAttackClass(String name, String damageClass);

  @Query("{$and: [ {'power':'?0'}, {'attackType':'?1'} ] }")
  List<Move> findByPowerAndAttackType(int power, String type);

  @Query("{$and: [ {'power':'?0'}, {'attackClass':'?1'} ] }")
  List<Move> findByPowerAndAttackClass(int power, String damageClass);

  @Query("{$and: [ {'attackClass':'?0'}, {'attackType':'?1'} ] }")
  List<Move> findByAttackClassAndAttackType(String damageClass, String type);
}
