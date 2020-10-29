package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class GenerationPokemonMovesList {
  private List<GenerationMove> moves;

  public GenerationPokemonMovesList() {
    this.moves = new ArrayList<>();
  }

  public List<GenerationMove> getMoves() {
    return moves;
  }

  public void setMoves(List<GenerationMove> moves) {
    this.moves = moves;
  }
}
