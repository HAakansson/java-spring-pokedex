package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class PokemonMoveList {
    private List<PokemonMove> moves;

    public PokemonMoveList() {
    }

    public PokemonMoveList(List<PokemonMove> moves) {
        this.moves = new ArrayList<>();
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(List<PokemonMove> pokemonMoves) {
        this.moves = pokemonMoves;
    }
}
