package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class GenerationPokemonList {
  private List<GenerationPokemon> pokemon_species;

  public GenerationPokemonList() {
    this.pokemon_species = new ArrayList<>();
  }

  public List<GenerationPokemon> getPokemon_species() {
    return pokemon_species;
  }

  public void setPokemon_species(List<GenerationPokemon> pokemon_species) {
    this.pokemon_species = pokemon_species;
  }
}
