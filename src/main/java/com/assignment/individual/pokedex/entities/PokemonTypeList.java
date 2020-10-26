package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonTypeList {
    List<PokemonType> types;

    public PokemonTypeList() {
    }

    public PokemonTypeList(List<PokemonType> types) {
        this.types = new ArrayList<>();
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> pokemonTypes) {
        this.types = pokemonTypes;
    }
}
