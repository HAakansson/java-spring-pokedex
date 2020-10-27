package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class MoveTypeObject {
    private HashMap<String, String> type;

    public MoveTypeObject() {
        this.type = new HashMap<>();
    }

    public HashMap<String, String> getType() {
        return type;
    }

    public void setType(HashMap<String, String> type) {
        this.type = type;
    }
}
