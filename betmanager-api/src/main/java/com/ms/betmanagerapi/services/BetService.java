package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    @Autowired
    BetRepository betRepository;
    @Autowired
    ValidationService validationService;
    @Autowired
    ProcessingService processingService;


    public List<BetModel> getAll(){
        return betRepository.findAll();
    }

    public BetModel create(BetModel betModel){
        if(validationService.validadeBetReferences(betModel))
            if (validationService.validadeNumbers(betModel.getNumbers()))
                return betRepository.save(betModel);
        return null;
    }

    public BetModel createLittleSurprise(BetModel betModel){
        if(validationService.validadeBetReferences(betModel)) {
            do {
                betModel.setNumbers(processingService.getLittleSurprise());
            } while (!validationService.validadeNumbers(betModel.getNumbers()));

            return betRepository.save(betModel);
        }

        return null;
    }

}
