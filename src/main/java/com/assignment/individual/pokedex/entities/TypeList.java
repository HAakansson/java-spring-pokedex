package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class TypeList {
    private List<TypeBaseInfo> results;

    public TypeList() {
    }

    public TypeList(List<TypeBaseInfo> results) {
        this.results = new ArrayList<>();
    }

    public List<TypeBaseInfo> getResults() {
        return results;
    }

    public void setResults(List<TypeBaseInfo> results) {
        this.results = results;
    }
}
