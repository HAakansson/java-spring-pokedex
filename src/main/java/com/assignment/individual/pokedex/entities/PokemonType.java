package com.assignment.individual.pokedex.entities;

import org.springframework.data.annotation.Id;

import java.util.HashMap;

public class PokemonType {
    private int slot;
    private HashMap<String, String> type;

    public PokemonType() {
    }

    public PokemonType(int slot, HashMap<String, String> type) {
        this.slot = slot;
        this.type = new HashMap<>();
    }

    public HashMap<String, String> getType() {
        return type;
    }

    public void setType(HashMap<String, String> type) {
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
