package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.BetDTO;
import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.services.BetService;
import com.ms.betmanagerapi.services.ProcessingService;
import com.ms.betmanagerapi.services.ValidationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bet")
public class BetController {

    @Autowired
    BetService betService;
    @Autowired
    ValidationService validationService;

    @Autowired
    ProcessingService processingService;

    @GetMapping("/")
    public ResponseEntity<List<BetModel>> getAll(){
        List<BetModel> allBets = betService.getAll();
        return new ResponseEntity<>(allBets, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BetModel> create(@RequestBody BetDTO betDTO){

        BetModel betModel = new BetModel();
        BeanUtils.copyProperties(betDTO, betModel);
        if(validationService.validadeBetReferences(betModel))
            if(validationService.validadeBetNumbers(betModel.getNumbers()))
                return new ResponseEntity<>(betService.create(betModel), HttpStatus.CREATED);

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/createLittleSurprise")
    public ResponseEntity<BetModel> createLittleSurprise(@RequestBody BetDTO betDTO){
        BetModel betModel = new BetModel();
        BeanUtils.copyProperties(betDTO, betModel);

        if(validationService.validadeBetReferences(betModel)) {
            do {
                String l = processingService.getLittleSurprise();
                System.out.println(l);
                betModel.setNumbers(l);
            }while(!validationService.validadeBetNumbers(betModel.getNumbers()));

            return new ResponseEntity<>(betService.create(betModel), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
