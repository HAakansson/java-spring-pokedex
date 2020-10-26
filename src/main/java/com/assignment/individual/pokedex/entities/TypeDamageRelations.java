package com.assignment.individual.pokedex.entities;

import java.util.ArrayList;
import java.util.List;

public class TypeDamageRelations {
    private List<TypeDamageObject> double_damage_from;
    private List<TypeDamageObject> double_damage_to;
    private List<TypeDamageObject> half_damage_from;
    private List<TypeDamageObject> half_damage_to;
    private List<TypeDamageObject> no_damage_from;
    private List<TypeDamageObject> no_damage_to;

    public TypeDamageRelations() {
        this.double_damage_from = new ArrayList<>();
        this.double_damage_to = new ArrayList<>();
        this.half_damage_from = new ArrayList<>();
        this.half_damage_to = new ArrayList<>();
        this.no_damage_from = new ArrayList<>();
        this.no_damage_to = new ArrayList<>();
    }

    public List<TypeDamageObject> getDouble_damage_from() {
        return double_damage_from;
    }

    public void setDouble_damage_from(List<TypeDamageObject> double_damage_from) {
        this.double_damage_from = double_damage_from;
    }

    public List<TypeDamageObject> getDouble_damage_to() {
        return double_damage_to;
    }

    public void setDouble_damage_to(List<TypeDamageObject> double_damage_to) {
        this.double_damage_to = double_damage_to;
    }

    public List<TypeDamageObject> getHalf_damage_from() {
        return half_damage_from;
    }

    public void setHalf_damage_from(List<TypeDamageObject> half_damage_from) {
        this.half_damage_from = half_damage_from;
    }

    public List<TypeDamageObject> getHalf_damage_to() {
        return half_damage_to;
    }

    public void setHalf_damage_to(List<TypeDamageObject> half_damage_to) {
        this.half_damage_to = half_damage_to;
    }

    public List<TypeDamageObject> getNo_damage_from() {
        return no_damage_from;
    }

    public void setNo_damage_from(List<TypeDamageObject> no_damage_from) {
        this.no_damage_from = no_damage_from;
    }

    public List<TypeDamageObject> getNo_damage_to() {
        return no_damage_to;
    }

    public void setNo_damage_to(List<TypeDamageObject> no_damage_to) {
        this.no_damage_to = no_damage_to;
    }
}
