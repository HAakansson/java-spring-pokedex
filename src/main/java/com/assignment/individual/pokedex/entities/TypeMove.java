package com.assignment.individual.pokedex.entities;

public class TypeMove {
    String name;
    String url;

    public TypeMove() {
    }

    public TypeMove(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
