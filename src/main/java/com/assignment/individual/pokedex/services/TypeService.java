package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.*;
import com.assignment.individual.pokedex.repositories.TypeBaseInfoRepo;
import com.assignment.individual.pokedex.repositories.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {
  private final RestTemplate restTemplate;

  public TypeService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Autowired
  TypeRepo typeRepo;
  @Autowired
  TypeBaseInfoRepo typeBaseInfoRepo;
  @Autowired
  TypeBaseInfoService typeBaseInfoService;

  public List<Type> getAllTypes() {
    var types = typeRepo.findAll();
    if (types.isEmpty()) {
      return this.getAllTypesFromPokeAPI();
    }
    return types;
  }

  public List<Type> getTypeByQuery(String name, String move, String pokemon, String doubleDamageTo) {
    return null;
  }

  public Type getTypeByTypeId(int id) {
    Type type = typeRepo.findByTypeId(id);
    if (type == null) {
      List<TypeBaseInfo> listOfTypeBaseInfo = typeBaseInfoRepo.findAll();
      if (listOfTypeBaseInfo.isEmpty()) {
        listOfTypeBaseInfo = typeBaseInfoService.getAllTypesAvailable();
      }
      for (var m : listOfTypeBaseInfo) {
        if (m.getUrl().split("/")[6].equals(Integer.toString(id))) {
          return this.getTypeByQuery(m.getName(), null, null, null).get(0);
        }
      }
    }
    if (type == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no type with that typeId.");
    }
    return type;
  }

  public Type save(Type type) {
    return typeRepo.save(type);
  }

  public void update(int id, Type type) {
    if (!typeRepo.existsByTypeId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any type with that type id");
    }
    type.setTypeId(id);
    typeRepo.save(type);
  }

  public void delete(int id) {
    if (!typeRepo.existsByTypeId(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any type with that type id");
    }
    typeRepo.deleteByTypeId(id);
  }

  public Type getTypeByName(String name) {
    Type type = typeRepo.findByName(name);
    if (type == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There does not exist a type with that name.");
    }
    return type;
  }

  public List<Type> getAllTypesFromPokeAPI() {
    List<Type> types = new ArrayList<>();
    var allAvailableTypes = typeBaseInfoService.getAllTypesAvailable();
    for (var t : allAvailableTypes) {
      Type type = restTemplate.getForObject(t.getUrl(), Type.class);
      TypeDamageRelationsObject typeDamageRelationsObject = restTemplate.getForObject(t.getUrl(), TypeDamageRelationsObject.class);
      TypePokemonList typePokemonList = restTemplate.getForObject(t.getUrl(), TypePokemonList.class);
      TypeMoveList typeMoveList = restTemplate.getForObject(t.getUrl(), TypeMoveList.class);
      for (var e : typeDamageRelationsObject.getDamage_relations().getDouble_damage_from()) {
        type.getDoubleDamageFrom().add(e.getName());
      }
      for (var e : typeDamageRelationsObject.getDamage_relations().getDouble_damage_to()) {
        type.getDoubleDamageTo().add(e.getName());
      }
      for (var e : typeDamageRelationsObject.getDamage_relations().getHalf_damage_from()) {
        type.getHalfDamageFrom().add(e.getName());
      }
      for (var e : typeDamageRelationsObject.getDamage_relations().getHalf_damage_to()) {
        type.getHalfDamageTo().add(e.getName());
      }
      for (var e : typeDamageRelationsObject.getDamage_relations().getNo_damage_from()) {
        type.getNoDamageFrom().add(e.getName());
      }
      for (var e : typeDamageRelationsObject.getDamage_relations().getNo_damage_to()) {
        type.getNoDamageTo().add(e.getName());
      }
      for (var e : typePokemonList.getPokemon()) {
        type.getPokemonList().add(e.getPokemon().get("name"));
      }
      for (var e : typeMoveList.getMoves()) {
        type.getMoveList().add(e.getName());
      }
      type.setUrl(t.getUrl());
      type = typeRepo.save(type);
      types.add(type);
    }
    return types;
  }
}
