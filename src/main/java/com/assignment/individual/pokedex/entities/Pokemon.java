package com.assignment.individual.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Pokemon {
    @Id
    String dbId;
    String name;
    @JsonProperty("id")
    int pokemonId;
    int height;
    int weight;
    List<Type> types;
    List<Move> moves;

}