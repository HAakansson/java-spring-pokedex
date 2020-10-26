package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class TypeMoveList {
    List<TypeMove> moves;

    public TypeMoveList() {
        this.moves = new ArrayList<>();
    }

    public List<TypeMove> getMoves() {
        return moves;
    }

    public void setMoves(List<TypeMove> moves) {
        this.moves = moves;
    }
}
