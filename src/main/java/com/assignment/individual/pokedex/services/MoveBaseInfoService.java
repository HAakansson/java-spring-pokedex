package com.assignment.individual.pokedex.services;

import com.assignment.individual.pokedex.entities.MoveBaseInfo;
import com.assignment.individual.pokedex.entities.MoveList;
import com.assignment.individual.pokedex.repositories.MoveBaseInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MoveBaseInfoService {
    private final RestTemplate restTemplate;
    public static final String MOVES_URL = "https://pokeapi.co/api/v2/move?offset=0&limit=813";

    public MoveBaseInfoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    MoveBaseInfoRepo moveBaseInfoRepo;

    public List<MoveBaseInfo> getAllMovesAvailable() {
        List<MoveBaseInfo> movesBaseInfoFromDB = moveBaseInfoRepo.findAll();
        if (movesBaseInfoFromDB.isEmpty()) {
            MoveList response = restTemplate.getForObject(MOVES_URL, MoveList.class);
            List<MoveBaseInfo> moveList = response.getResults();
            for (var m : moveList) {
                moveBaseInfoRepo.save(m);
            }
            return moveList;
        }
        return movesBaseInfoFromDB;
    }
}
