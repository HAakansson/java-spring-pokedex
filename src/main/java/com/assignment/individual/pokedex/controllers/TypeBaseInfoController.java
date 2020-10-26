package com.assignment.individual.pokedex.controllers;

import com.assignment.individual.pokedex.entities.TypeBaseInfo;
import com.assignment.individual.pokedex.services.TypeBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allAvailableTypes")
public class TypeBaseInfoController {

    @Autowired
    TypeBaseInfoService typeBaseInfoService;

    @GetMapping
    public ResponseEntity<List<TypeBaseInfo>> getAllTypesAvailable(){
        List<TypeBaseInfo> typesAvailable =  typeBaseInfoService.getAllTypesAvailable();
        return ResponseEntity.ok(typesAvailable);
    }
}
