package com.assignment.individual.pokedex.entities;

import org.springframework.data.annotation.Id;

public class TypeBaseInfo {
    @Id
    String id;
    String name;
    String url;

    public TypeBaseInfo() {
    }

    public TypeBaseInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
