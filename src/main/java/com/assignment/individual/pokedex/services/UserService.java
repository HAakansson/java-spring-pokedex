package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.User;
import com.assignment.individual.pokedex.repositories.UserRepo;
import com.assignment.individual.pokedex.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            User user = userRepo.findByUsername(username).orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Could not find a user with that specific username %s", username))
            );
            return List.of(user);
        }
        return userRepo.findAll();
    }

    public User findById(String id) {
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
}
