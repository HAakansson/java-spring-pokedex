package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonMoveList {
    private List<PokemonMove> pokemonMoves;

    public PokemonMoveList() {
    }

    public PokemonMoveList(List<PokemonMove> pokemonMoves) {
        this.pokemonMoves = new ArrayList<>();
    }

    public List<PokemonMove> getMoves() {
        return pokemonMoves;
    }

    public void setMoves(List<PokemonMove> pokemonMoves) {
        this.pokemonMoves = pokemonMoves;
    }
}
