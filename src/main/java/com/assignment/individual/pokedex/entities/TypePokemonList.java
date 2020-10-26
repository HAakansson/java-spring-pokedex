package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class TypePokemonList {
    List<TypePokemon> pokemon;

    public TypePokemonList() {
        this.pokemon = new ArrayList<>();
    }

    public List<TypePokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<TypePokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
