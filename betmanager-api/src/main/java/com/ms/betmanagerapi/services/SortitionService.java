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


    public SortitionModel create(SortitionModel sortitionModel){
        if(validadeNumbers(sortitionModel.getNumbers()))
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
        }while(!validadeNumbers(numbers));

        sortitionModel.setNumbers(numbers);
        return sortitionRepository.save(sortitionModel);
    }

    public SortitionModel addPredefinedNumber(SortitionModel sortitionModel, Integer predefinedNumber){
        String numbers;


        numbers = sortitionModel.getNumbers() + "," + predefinedNumber;
        if(validadeNumbers(numbers)){
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
        }while(!validadeNumbers(sortitionModel.getNumbers()));

        return sortitionRepository.save(sortitionModel);
    }
    public List<SortitionModel> getAll(){
        return sortitionRepository.findAll();
    }

    public SortitionModel getById(Integer id){
        return sortitionRepository.getById(id);
    }

    public Boolean validadeNumbers(String numbers){
        String[] split = numbers.split(",");

        if (split.length < 5 || split.length > 30)
            return false;

        return processingService.validadeNumbersIntegrity(split);
    }

}
