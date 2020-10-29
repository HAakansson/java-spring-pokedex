package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class GenerationPokemon {
  private HashMap<String, String> pokemon;

  public GenerationPokemon() {
    this.pokemon = new HashMap<>();
  }

  public HashMap<String, String> getPokemon() {
    return pokemon;
  }

  public void setPokemon(HashMap<String, String> pokemon) {
    this.pokemon = pokemon;
  }
}
