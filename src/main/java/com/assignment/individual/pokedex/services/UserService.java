package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.User;
import com.assignment.individual.pokedex.repositories.UserRepo;
import com.assignment.individual.pokedex.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;
  @Autowired
  MyUserDetailsService myUserDetailsService;

  public List<User> findAll(String username) {
    if (username != null) {
      if (isLoggedInUserAdmin() || username.equals(getLoggedInUsername())) {
        User user = userRepo.findByUsername(username).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Could not find a user with that specific username %s", username))
        );
        return List.of(user);
      } else {
        throw new ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "You can not request any other user than your own.");

      }

    }
    if (!isLoggedInUserAdmin()) {
      throw new ResponseStatusException(
          HttpStatus.FORBIDDEN,
          "You must be a ADMIN in order to request all the user.");
    }
    return userRepo.findAll();
  }

  public User findById(String id) {
    if (!isLoggedInUserAdmin()) {
      if (!getLoggedInUsersId().equals(id))
        throw new ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "You can not request any other user than your own.");
    }
    return userRepo.findById(id).orElseThrow(() ->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            String.format("Could not find a user with that specific id %s", id))
    );
  }

  public User findByUsername(String username) {
    return userRepo.findByUsername(username).orElseThrow(() ->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            String.format("Could not find a user with that specific username %s", username))
    );
  }

  public User saveUser(User user) {
    if (StringUtils.isEmpty(user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "You can not create a new user without a password.");
    }
    user.setPassword(myUserDetailsService.getEncoder().encode(user.getPassword()));
    return userRepo.save(user);
  }

  public void updateUser(String id, User user) {
    if (!isLoggedInUserAdmin()) {
      if (!getLoggedInUsersId().equals(id))
        throw new ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "You can not request any other user than your own.");
    }
    if (!userRepo.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          String.format("Could not find a user with id %s.", id));
    }
    user.setId(id);
    user.setPassword(myUserDetailsService.getEncoder().encode(user.getPassword()));
    userRepo.save(user);
  }

  public void delete(String id) {
    if (!userRepo.existsById(id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          String.format("Could not find a user with id %s.", id));
    }
    userRepo.deleteById(id);
  }

  public String getLoggedInUsersId() {
    String username = myUserDetailsService.getCurrentUser();
    User user = findByUsername(username);
    return user.getId();
  }

  public String getLoggedInUsername() {
    return myUserDetailsService.getCurrentUser();
  }

  public boolean isLoggedInUserAdmin() {
    String username = myUserDetailsService.getCurrentUser();
    User user = findByUsername(username);
    return user.getRoles().contains("ADMIN");
  }
}

