package com.assignment.individual.pokedex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.assignment.individual.pokedex.security.ApplicationUserPermisson.*;
import static com.assignment.individual.pokedex.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override // Basic authentication
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/pokemons/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/pokemons").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
                .antMatchers(HttpMethod.PUT).hasAuthority(API_WRITE.getPermisson())
                .antMatchers(HttpMethod.POST).hasAuthority(API_WRITE.getPermisson())
                .antMatchers(HttpMethod.DELETE).hasAuthority(API_WRITE.getPermisson())
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails niklasUser = User.builder()
                .username("niklas")
                .password(passwordEncoder.encode("password"))
//                .roles(USER.name()) //ROLE_USER
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails mariaUser = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails elsaUser = User.builder()
                .username("elsa")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMINROOKIE.name())
                .authorities(ADMINROOKIE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                niklasUser,
                mariaUser,
                elsaUser
        );
    }
}
