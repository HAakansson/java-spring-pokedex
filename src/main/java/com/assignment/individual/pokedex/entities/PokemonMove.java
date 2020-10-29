package com.assignment.individual.pokedex.entities;

import org.springframework.data.annotation.Id;

import java.util.HashMap;

public class PokemonMove {
    private HashMap<String, String> move;

    public PokemonMove() {
        this.move = new HashMap<>();
    }

    public HashMap<String, String> getMove() {
        return move;
    }

    public void setMove(HashMap<String, String> move) {
        this.move = move;
    }
}
