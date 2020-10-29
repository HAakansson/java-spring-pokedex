package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class GenerationMove {
  private HashMap<String, String> move;

  public GenerationMove() {
    this.move = new HashMap<>();
  }

  public HashMap<String, String> getMove() {
    return move;
  }

  public void setMove(HashMap<String, String> move) {
    this.move = move;
  }
}
