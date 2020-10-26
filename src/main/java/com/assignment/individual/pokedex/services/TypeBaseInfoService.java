package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.PokemonBaseInfo;
import com.assignment.individual.pokedex.entities.PokemonList;
import com.assignment.individual.pokedex.entities.TypeBaseInfo;
import com.assignment.individual.pokedex.entities.TypeList;
import com.assignment.individual.pokedex.repositories.TypeBaseInfoRepo;
import com.fasterxml.jackson.databind.type.TypeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TypeBaseInfoService {
    private final RestTemplate restTemplate;
    public static final String TYPES_URL = "https://pokeapi.co/api/v2/type";

    public TypeBaseInfoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    TypeBaseInfoRepo typeBaseInfoRepo;

    public List<TypeBaseInfo> getAllTypesAvailable() {
        List<TypeBaseInfo> typesBaseInfoFromDB = typeBaseInfoRepo.findAll();
        if (typesBaseInfoFromDB.isEmpty()) {
            TypeList response = restTemplate.getForObject(TYPES_URL, TypeList.class);
            List<TypeBaseInfo> typeList = response.getResults();
            for (var t : typeList) {
                typeBaseInfoRepo.save(t);
            }
            return typeList;
        }
        return typesBaseInfoFromDB;
    }
}
