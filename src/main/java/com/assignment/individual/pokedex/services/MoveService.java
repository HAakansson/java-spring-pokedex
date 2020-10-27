package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.Move;
import com.assignment.individual.pokedex.entities.MoveDamageClassObject;
import com.assignment.individual.pokedex.entities.MoveTypeObject;
import com.assignment.individual.pokedex.repositories.MoveBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.MoveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoveService {
  private final RestTemplate restTemplate;

  public MoveService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Autowired
  MoveRepo moveRepo;
  @Autowired
  MoveBaseInfoRepo moveBaseInfoRepo;

  public List<Move> getAllMoves() {
    return moveRepo.findAll();
  }

  public List<Move> getMovesByQuery(String name, String power, String damageClass, String type) {
    List<Move> moves = new ArrayList<>();

    if(name != null){
      moves = getMovesByNameFromPokeAPI(name);
    }
    return moves;
  }

  public List<Move> getMovesByNameFromPokeAPI(String name) {
    List<Move> moves = new ArrayList<>();
    var listOfMoveBaseInfo = moveBaseInfoRepo.findByNameContaining(name);
    if (!listOfMoveBaseInfo.isEmpty()) {
      for (var m : listOfMoveBaseInfo) {
        Move move = restTemplate.getForObject(m.getUrl(), Move.class);
        MoveDamageClassObject moveDamageClassObject = restTemplate.getForObject(m.getUrl(), MoveDamageClassObject.class);
        MoveTypeObject moveTypeObject = restTemplate.getForObject(m.getUrl(), MoveTypeObject.class);
        move.setAttackClass(moveDamageClassObject.getDamage_class().get("name"));
        move.setAttackType(moveTypeObject.getType().get("name"));
        move.setUrl(m.getUrl());
        move = moveRepo.save(move);
        moves.add(move);
      }
      return moves;
    }
    return moves;
  }
}
