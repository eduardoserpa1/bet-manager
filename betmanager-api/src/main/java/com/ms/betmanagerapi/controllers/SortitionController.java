package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.SortitionDTO;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.ProcessingService;
import com.ms.betmanagerapi.services.SortitionService;
import com.ms.betmanagerapi.services.ValidationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sortition")
public class SortitionController {

    @Autowired
    SortitionService sortitionService;

    @Autowired
    ProcessingService processingService;

    @Autowired
    ValidationService validationService;

    @GetMapping("/")
    public ResponseEntity<List<SortitionModel>> getAll(){
        List<SortitionModel> allSortitions = sortitionService.getAll();
        return new ResponseEntity<>(allSortitions, HttpStatus.OK);
    }

    @PostMapping("/createPredefined")
    public ResponseEntity<SortitionModel> createPredefined(@RequestBody SortitionDTO sortitionDTO){
        SortitionModel sortitionModel = new SortitionModel();
        BeanUtils.copyProperties(sortitionDTO,sortitionModel);
        SortitionModel sortitionModelResponse = sortitionService.create(sortitionModel);
        return new ResponseEntity<>(sortitionModelResponse, HttpStatus.CREATED);
    }

    @PostMapping("/createRandom")
    public ResponseEntity<SortitionModel> createRandomBet(@RequestBody SortitionDTO sortitionDTO){
        SortitionModel sortitionModel = new SortitionModel();
        BeanUtils.copyProperties(sortitionDTO,sortitionModel);

        SortitionModel sortitionModelResponse = sortitionService.createRandom(sortitionModel);
        return new ResponseEntity<>(sortitionModelResponse, HttpStatus.CREATED);
    }

    @PutMapping("/addRandomNumberById/{id}")
    public ResponseEntity<SortitionModel> addNumberById(@PathVariable(value = "id") Integer sortitionId){
        SortitionModel sortitionModel = sortitionService.getById(sortitionId);

        SortitionModel sortitionModelResponse = sortitionService.addRandomNumber(sortitionModel);
        return new ResponseEntity<>(sortitionModelResponse, HttpStatus.OK);
    }

}
