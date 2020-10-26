package com.assignment.individual.pokedex.entities;

import java.util.HashMap;

public class TypePokemon {
    HashMap<String, String> pokemon;

    public TypePokemon() {
        this.pokemon = new HashMap<>();
    }

    public HashMap<String, String> getPokemon() {
        return pokemon;
    }

    public void setPokemon(HashMap<String, String> pokemon) {
        this.pokemon = pokemon;
    }
}
