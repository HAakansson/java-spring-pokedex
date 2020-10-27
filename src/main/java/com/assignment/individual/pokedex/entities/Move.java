package com.assignment.individual.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Move {
  @Id
  String dbId;
  String url;
  String name;
  String attackType = "";
  String attackClass = "";
  @JsonProperty("id")
  int moveId;
  int power;
  int pp;
  int accuracy;

  public Move() {
  }

  public Move(String url, int accuracy, int moveId, String name, int power, int pp) {
    this.url = url;
    this.accuracy = accuracy;
    this.moveId = moveId;
    this.name = name;
    this.power = power;
    this.pp = pp;
  }

  public String getDbId() {
    return dbId;
  }

  public void setDbId(String dbId) {
    this.dbId = dbId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
