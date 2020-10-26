package com.assignment.individual.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Type {
    @Id
    String dbId;
    @JsonProperty("id")
    int typeId;
    String name;
    String url;
    List<String> doubleDamageFrom = new ArrayList<>();
    List<String> doubleDamageTo = new ArrayList<>();
    List<String> halfDamageFrom = new ArrayList<>();
    List<String> halfDamageTo = new ArrayList<>();
    List<String> noDamageFrom = new ArrayList<>();
    List<String> noDamageTo = new ArrayList<>();
    List<String> moveList = new ArrayList<>();
    List<String> pokemonList = new ArrayList<>();

    public Type() {
    }

    public Type(int typeId, String name, String url) {
        this.typeId = typeId;
        this.name = name;
        this.url = url;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<String> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }

    public List<String> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public void setDoubleDamageTo(List<String> doubleDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<String> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public void setHalfDamageFrom(List<String> halfDamageFrom) {
        this.halfDamageFrom = halfDamageFrom;
    }

    public List<String> getHalfDamageTo() {
        return halfDamageTo;
    }

    public void setHalfDamageTo(List<String> halfDamageTo) {
        this.halfDamageTo = halfDamageTo;
    }

    public List<String> getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(List<String> noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public List<String> getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(List<String> noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    public List<String> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<String> moveList) {
        this.moveList = moveList;
    }

    public List<String> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<String> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
