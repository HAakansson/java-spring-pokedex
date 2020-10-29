package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class GenerationList {
  List<GenerationBaseInfo> results;

  public GenerationList() {
    this.results = new ArrayList<>();
  }

  public List<GenerationBaseInfo> getResults() {
    return results;
  }

  public void setResults(List<GenerationBaseInfo> results) {
    this.results = results;
  }
}
