package com.assignment.individual.pokedex.security;

import com.assignment.individual.pokedex.entities.User;
import com.assignment.individual.pokedex.repositories.UserRepo;
import com.assignment.individual.pokedex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    @PostConstruct
    private void createDefaultUsers() {
        if (userRepo.findByUsername("niklas").isEmpty()) {
            addUser("niklas", "password", Set.of("USER"));
        }
        if (userRepo.findByUsername("maria").isEmpty()) {
            addUser("maria", "password", Set.of("ADMIN"));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        return toUserDetails(user);
    }

    public User addUser(String username, String password, Set<String> roles) {
        User user = new User(username, encoder.encode(password), roles);
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getGrantedAuthorities(user)).build();
    }

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }
}
