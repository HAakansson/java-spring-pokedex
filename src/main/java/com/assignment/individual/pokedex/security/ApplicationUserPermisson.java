package com.assignment.individual.pokedex.security;

public enum ApplicationUserPermisson {
  USER_READ("user:read"),
  USER_WRITE("user:write"),
  API_READ("api:read"),
  API_WRITE("api:write");

  private final String permisson;

  ApplicationUserPermisson(String permisson) {
    this.permisson = permisson;
  }

  public String getPermisson() {
    return permisson;
  }
}
