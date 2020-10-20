package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonSummaryList {
    private List<PokemonSummary> results;

    public PokemonSummaryList() {
    }

    public PokemonSummaryList(List<PokemonSummary> results) {
        this.results = new ArrayList<>();
    }

    public List<PokemonSummary> getResults() {
        return results;
    }

    public void setResults(List<PokemonSummary> results) {
        this.results = results;
    }
}
