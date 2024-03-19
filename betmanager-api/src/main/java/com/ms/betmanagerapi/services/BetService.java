package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.BetRepository;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {

    @Autowired
    BetRepository betRepository;

    @Autowired
    SortitionRepository sortitionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProcessingService processingService;


    public List<BetModel> getAll(){
        return betRepository.findAll();
    }

    public BetModel create(BetModel betModel){
        if(validadeBetReferences(betModel))
            if (validadeNumbers(betModel.getNumbers()))
                return betRepository.save(betModel);
        return null;
    }

    public BetModel createLittleSurprise(BetModel betModel){
        if(validadeBetReferences(betModel)) {
            do {
                betModel.setNumbers(processingService.getLittleSurprise());
            } while (!validadeNumbers(betModel.getNumbers()));

            return betRepository.save(betModel);
        }

        return null;
    }

    public List<BetModel> getAllBySortitionId(Integer id){
        return betRepository.getAllBySortitionId(id);
    }

    public Boolean validadeBetReferences(BetModel betModel){
        Optional<UserModel> userModel = userRepository.findById(betModel.getIdUser());
        Optional<SortitionModel> sortitionModel = sortitionRepository.findById(betModel.getIdSortition());

        return userModel.isPresent() && sortitionModel.isPresent();
    }

    public Boolean validadeNumbers(String numbers){
        String[] split = numbers.split(",");

        if (split.length != 5)
            return false;

        return processingService.validadeNumbersIntegrity(split);
    }
}
