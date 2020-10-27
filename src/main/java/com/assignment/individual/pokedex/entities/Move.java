package com.assignment.individual.pokedex.entities;

import org.springframework.data.annotation.Id;

public class Move {
    @Id
    String id;
    int accuracy;
    int moveId;
    String name;
    int power;
    int pp;
    String attackType = "";
    String attackClass = "";

    public Move() {
    }

    public Move(int accuracy, int moveId, String name, int power, int pp) {
        this.accuracy = accuracy;
        this.moveId = moveId;
        this.name = name;
        this.power = power;
        this.pp = pp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getAttackClass() {
        return attackClass;
    }

    public void setAttackClass(String attackClass) {
        this.attackClass = attackClass;
    }
}
