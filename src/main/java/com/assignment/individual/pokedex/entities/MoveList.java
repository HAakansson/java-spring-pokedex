package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class MoveList {
    List<MoveBaseInfo> results;

    public MoveList() {
        this.results = new ArrayList<>();
    }

    public List<MoveBaseInfo> getResults() {
        return results;
    }

    public void setResults(List<MoveBaseInfo> results) {
        this.results = results;
    }
}
