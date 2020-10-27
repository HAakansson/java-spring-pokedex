package com.assignment.individual.pokedex.repositories;

import com.assignment.individual.pokedex.entities.Move;
import com.assignment.individual.pokedex.entities.MoveBaseInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveBaseInfoRepo extends MongoRepository<MoveBaseInfo, String> {

  List<MoveBaseInfo> findByNameContaining(String name);
}
