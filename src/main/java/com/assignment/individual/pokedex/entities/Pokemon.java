package com.assignment.individual.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Pokemon {
    @Id
    String dbId;
    String name;
    String url;
    @JsonProperty("id")
    int pokemonId;
    int height;
    int weight;
    List<String> types;
    List<String> moves;

    public Pokemon() {
    }

    public Pokemon(String name, String url, int pokemonId, int height, int weight, List<String> types, List<String> moves) {
        this.name = name;
        this.url = url;
        this.pokemonId = pokemonId;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.moves = moves;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }
}