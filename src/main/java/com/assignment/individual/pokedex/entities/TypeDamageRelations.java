package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class TypeDamageRelations {
    private List<DamageObject> double_damage_from;
    private List<DamageObject> double_damage_to;
    private List<DamageObject> half_damage_from;
    private List<DamageObject> half_damage_to;
    private List<DamageObject> no_damage_from;
    private List<DamageObject> no_damage_to;

    public TypeDamageRelations() {
        this.double_damage_from = new ArrayList<>();
        this.double_damage_to = new ArrayList<>();
        this.half_damage_from = new ArrayList<>();
        this.half_damage_to = new ArrayList<>();
        this.no_damage_from = new ArrayList<>();
        this.no_damage_to = new ArrayList<>();
    }

    public List<DamageObject> getDouble_damage_from() {
        return double_damage_from;
    }

    public void setDouble_damage_from(List<DamageObject> double_damage_from) {
        this.double_damage_from = double_damage_from;
    }

    public List<DamageObject> getDouble_damage_to() {
        return double_damage_to;
    }

    public void setDouble_damage_to(List<DamageObject> double_damage_to) {
        this.double_damage_to = double_damage_to;
    }

    public List<DamageObject> getHalf_damage_from() {
        return half_damage_from;
    }

    public void setHalf_damage_from(List<DamageObject> half_damage_from) {
        this.half_damage_from = half_damage_from;
    }

    public List<DamageObject> getHalf_damage_to() {
        return half_damage_to;
    }

    public void setHalf_damage_to(List<DamageObject> half_damage_to) {
        this.half_damage_to = half_damage_to;
    }

    public List<DamageObject> getNo_damage_from() {
        return no_damage_from;
    }

    public void setNo_damage_from(List<DamageObject> no_damage_from) {
        this.no_damage_from = no_damage_from;
    }

    public List<DamageObject> getNo_damage_to() {
        return no_damage_to;
    }

    public void setNo_damage_to(List<DamageObject> no_damage_to) {
        this.no_damage_to = no_damage_to;
    }
}
