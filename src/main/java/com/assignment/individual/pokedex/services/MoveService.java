package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.*;
import com.assignment.individual.pokedex.repositories.MoveBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.MoveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  @Autowired
  MoveBaseInfoService moveBaseInfoService;

  public List<Move> getAllMoves() {
    return moveRepo.findAll();
  }

  public List<Move> getMovesByQuery(String name, String power, String damageClass, String type) {
    List<Move> moves = new ArrayList<>();

    System.out.println("MOVE SERVICE");
    System.out.println("name: " + name);
    System.out.println("power: " + power);
    System.out.println("damageClass: " + damageClass);
    System.out.println("type: " + type);

    if (name != null && power != null && damageClass != null && type != null) {
      moves = moveRepo.findByNameContainingAndPowerAndAttackClassAndType(name, Integer.parseInt(power), damageClass, type);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getPower() == Integer.parseInt(power) && m.getAttackClass().equals(damageClass) && m.getAttackType().equals(type))
            .collect(Collectors.toList());
      }

    } else if (name != null && power != null && damageClass != null) {
      moves = moveRepo.findByNameContainingAndPowerAndAttackClass(name, Integer.parseInt(power), damageClass);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getPower() == Integer.parseInt(power) && m.getAttackClass().equals(damageClass))
            .collect(Collectors.toList());
      }

    } else if (power != null && damageClass != null && type != null) {
      moves = moveRepo.findByPowerAndAttackClassAndAttackType(Integer.parseInt(power), damageClass, type);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that combination of queries, try adding a name as well.");
      }


    } else if (name != null & damageClass != null && type != null) {
      moves = moveRepo.findByNameContainingAndAttackClassAndAttackType(name, damageClass, type);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getAttackClass().equals(damageClass) && m.getAttackType().equals(type))
            .collect(Collectors.toList());
      }

    } else if (name != null && power != null && type != null) {
      moves = moveRepo.findByNameContainingAndPowerAndAttackType(name, Integer.parseInt(power), type);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getPower() == Integer.parseInt(power) && m.getAttackType().equals(type))
            .collect(Collectors.toList());
      }

    } else if (name != null && power != null) {
      moves = moveRepo.findByNameContainingAndPower(name, Integer.parseInt(power));
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getPower() == Integer.parseInt(power))
            .collect(Collectors.toList());
      }

    } else if (name != null && type != null) {
      moves = moveRepo.findByNameContainingAndAttackType(name, type);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getAttackType().equals(type))
            .collect(Collectors.toList());
      }

    } else if (name != null && damageClass != null) {
      moves = moveRepo.findByNameContainingAndAttackClass(name, damageClass);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        moves = moves.stream()
            .filter(m -> m.getAttackClass().equals(damageClass))
            .collect(Collectors.toList());
      }

    } else if (power != null && type != null) {
      moves = moveRepo.findByPowerAndAttackType(Integer.parseInt(power), type);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that combination of queries, try adding a name as well.");
      }

    } else if (power != null && damageClass != null) {
      moves = moveRepo.findByPowerAndAttackClass(Integer.parseInt(power), damageClass);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that combination of queries, try adding a name as well.");
      }

    } else if (damageClass != null && type != null) {
      moves = moveRepo.findByAttackClassAndAttackType(damageClass, type);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that combination of queries, try adding a name as well.");
      }

    } else if (name != null) {
      moves = moveRepo.findByNameContaining(name);
      if (moves.isEmpty()) {
        moves = getMovesByNameFromPokeAPI(name);
        if (moves.isEmpty()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in in the poke api with that name");
        }
      }

    } else if (power != null) {
      moves = moveRepo.findByPower(Integer.parseInt(power));
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that power.");
      }

    } else if (damageClass != null) {
      moves = moveRepo.findByAttackClass(damageClass);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that damage class.");
      }

    } else if (type != null) {
      moves = moveRepo.findByAttackType(type);
      if (moves.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move in the database with that type");
      }

    }
    return moves;
  }

  public Move getMoveByMoveId(int id) {
    Move move = moveRepo.findByMoveId(id);
    if (move == null) {
      List<MoveBaseInfo> listOfMoveBaseInfo = moveBaseInfoRepo.findAll();
      if (listOfMoveBaseInfo.isEmpty()) {
        listOfMoveBaseInfo = moveBaseInfoService.getAllMovesAvailable();
      }
      for (var m : listOfMoveBaseInfo) {
        if (m.getUrl().split("/")[6].equals(Integer.toString(id))) {
          return this.getMovesByQuery(m.getName(), null, null, null).get(0);
        }
      }
    }
    if (move == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no move with that moveId.");
    }
    return move;
  }

  public Move save(Move move) {
    return moveRepo.save(move);
  }

  public void update(int id, Move move) {
    if (!moveRepo.existsByMoveId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any move with that move id");
    }
    move.setMoveId(id);
    moveRepo.save(move);
  }

  public void delete(int id) {
    if (!moveRepo.existsByMoveId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any move with that move id");
    }
    moveRepo.deleteByMoveId(id);
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
