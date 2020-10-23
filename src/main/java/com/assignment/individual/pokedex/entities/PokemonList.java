package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonList {
    private List<PokemonBaseInfo> results;

    public PokemonList() {
    }

    public PokemonList(List<PokemonBaseInfo> results) {
        this.results = new ArrayList<>();
    }

    public List<PokemonBaseInfo> getResults() {
        return results;
    }

    public void setResults(List<PokemonBaseInfo> results) {
        this.results = results;
    }
}
