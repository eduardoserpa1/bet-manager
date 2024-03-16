package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.SortitionDTO;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.SortitionService;
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

    @GetMapping("/")
    public ResponseEntity<List<SortitionModel>> getAll(){
        List<SortitionModel> allSortitions = sortitionService.getAll();
        return new ResponseEntity<>(allSortitions, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SortitionModel> create(@RequestBody SortitionDTO sortitionDTO){
        SortitionModel sortitionModel = new SortitionModel();
        BeanUtils.copyProperties(sortitionDTO,sortitionModel);
        return new ResponseEntity<>(sortitionService.create(sortitionModel), HttpStatus.CREATED);
    }

}
