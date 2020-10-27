package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class MoveDamageClassObject {
    private HashMap<String, String> damage_class;

    public MoveDamageClassObject() {
        this.damage_class = new HashMap<>();
    }

    public HashMap<String, String> getDamage_class() {
        return damage_class;
    }

    public void setDamage_class(HashMap<String, String> damage_class) {
        this.damage_class = damage_class;
    }
}
