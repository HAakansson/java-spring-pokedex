package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.Move;
import com.assignment.individual.pokedex.repositories.MoveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveService {

  @Autowired
  MoveRepo moveRepo;

  public List<Move> getAllMoves() {
    return moveRepo.findAll();
  }

  public List<Move> getMovesByQuery(String name, String power, String damageClass, String type){
    return null;
  }
}
