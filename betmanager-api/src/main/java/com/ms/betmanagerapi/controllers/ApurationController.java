package com.ms.betmanagerapi.controllers;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.BetService;
import com.ms.betmanagerapi.services.SortitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apuration")
public class ApurationController {

    @Autowired
    BetService betService;
    @Autowired
    SortitionService sortitionService;

    @PostMapping("/makeApuration/{id}")
    public ResponseEntity<SortitionModel> makeApuration(@PathVariable(value = "id") Integer sortitionId){
        SortitionModel sortitionModel = sortitionService.getById(sortitionId);
        List<BetModel> bets = betService.getAllBySortitionId(sortitionId);
        SortitionModel sortitionModelResponse = sortitionService.makeApuration(sortitionModel, bets);
        return new ResponseEntity<>(sortitionModelResponse, HttpStatus.OK);
    }
}
