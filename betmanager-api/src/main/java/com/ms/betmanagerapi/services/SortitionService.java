package com.ms.betmanagerapi.services;

import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortitionService {

    @Autowired
    SortitionRepository sortitionRepository;
    @Autowired
    ProcessingService processingService;
    @Autowired
    ValidationService validationService;

    public SortitionModel create(SortitionModel sortitionModel){
        if(validationService.validadeNumbers(sortitionModel.getNumbers()))
            return sortitionRepository.save(sortitionModel);
        return null;
    }

    public SortitionModel addRandomNumber(SortitionModel sortitionModel){
        if(sortitionModel.getNumbers().split(",").length >= 30)
            return sortitionModel;
        String numbers;

        do{
            numbers = sortitionModel.getNumbers() + "," + processingService.getRandomNumber();
            System.out.println(numbers);
        }while(!validationService.validadeNumbers(numbers));

        sortitionModel.setNumbers(numbers);
        return sortitionRepository.save(sortitionModel);
    }

    public SortitionModel addPredefinedNumber(SortitionModel sortitionModel, Integer predefinedNumber){
        String numbers;


        numbers = sortitionModel.getNumbers() + "," + predefinedNumber;
        if(validationService.validadeNumbers(numbers)){
            sortitionModel.setNumbers(numbers);
            return sortitionRepository.save(sortitionModel);
        }

        return null;
    }

    public SortitionModel makeApuration(SortitionModel sortitionModel, List<BetModel> bets){
        int count = 0;
        while(!processingService.haveWinners(sortitionModel, bets) && count < 25){
            sortitionModel = addRandomNumber(sortitionModel);
            count++;
        }
        if (count < 25)
            sortitionModel.setIsFinished(true);

        sortitionRepository.save(sortitionModel);

        return sortitionModel;
    }

    public SortitionModel createRandom(SortitionModel sortitionModel){
        do {
            sortitionModel.setNumbers(processingService.getLittleSurprise());
        }while(!validationService.validadeNumbers(sortitionModel.getNumbers()));

        return sortitionRepository.save(sortitionModel);
    }
    public List<SortitionModel> getAll(){
        return sortitionRepository.findAll();
    }

    public SortitionModel getById(Integer id){
        return sortitionRepository.getById(id);
    }


}
