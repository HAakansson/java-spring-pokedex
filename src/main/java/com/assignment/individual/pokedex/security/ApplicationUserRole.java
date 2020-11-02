package com.assignment.individual.pokedex.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.assignment.individual.pokedex.security.ApplicationUserPermisson.*;

public enum ApplicationUserRole {
  ADMINROOKIE(Sets.newHashSet(API_READ, USER_READ)),
  USER(Sets.newHashSet()),
  ADMIN(Sets.newHashSet(API_READ, API_WRITE, USER_READ, USER_WRITE));

  private final Set<ApplicationUserPermisson> permissons;

  ApplicationUserRole(Set<ApplicationUserPermisson> permissons) {
    this.permissons = permissons;
  }

  public Set<ApplicationUserPermisson> getPermissons() {
    return permissons;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
    Set<SimpleGrantedAuthority> permissions = getPermissons().stream()
            .map(p -> new SimpleGrantedAuthority(p.getPermisson()))
            .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
