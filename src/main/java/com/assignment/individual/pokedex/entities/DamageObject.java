package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class DamageObject {
    String name;
    String url;

    public DamageObject() {
    }

    public DamageObject(String name, String url) {
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
