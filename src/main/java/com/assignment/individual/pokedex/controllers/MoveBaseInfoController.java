package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.MoveBaseInfo;
import com.assignment.individual.pokedex.services.MoveBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allAvailableMoves")
public class MoveBaseInfoController {

    @Autowired
    MoveBaseInfoService moveBaseInfoService;

    @GetMapping
    public ResponseEntity<List<MoveBaseInfo>> getAllMovesAvailable(){
        List<MoveBaseInfo> movesAvailable = moveBaseInfoService.getAllMovesAvailable();
        return ResponseEntity.ok(movesAvailable);
    }
}
