package com.assignment.individual.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Generation {

  @Id
  private String dbId;
  private String url;
  private String generationName;
  @JsonProperty("id")
  private int generationId;
  private List<String> pokemonMoves = new ArrayList<>();
  private List<String> pokemons = new ArrayList<>();

  public Generation() {
  }

  public Generation(String url, String generationName, int generationId) {
    this.url = url;
    this.generationName = generationName;
    this.generationId = generationId;
  }

  public String getDbId() {
    return dbId;
  }

  public void setDbId(String dbId) {
    this.dbId = dbId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getGenerationName() {
    return generationName;
  }

  public void setGenerationName(String generationName) {
    this.generationName = generationName;
  }

  public int getGenerationId() {
    return generationId;
  }

  public void setGenerationId(int generationId) {
    this.generationId = generationId;
  }

  public List<String> getPokemonMoves() {
    return pokemonMoves;
  }

  public void setPokemonMoves(List<String> pokemonMoves) {
    this.pokemonMoves = pokemonMoves;
  }

  public List<String> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<String> pokemons) {
    this.pokemons = pokemons;
  }
}
