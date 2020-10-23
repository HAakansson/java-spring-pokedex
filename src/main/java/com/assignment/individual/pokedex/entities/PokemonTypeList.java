package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonTypeList {
    List<PokemonType> pokemonTypes;

    public PokemonTypeList() {
    }

    public PokemonTypeList(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = new ArrayList<>();
    }

    public List<PokemonType> getTypes() {
        return pokemonTypes;
    }

    public void setTypes(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }
}
