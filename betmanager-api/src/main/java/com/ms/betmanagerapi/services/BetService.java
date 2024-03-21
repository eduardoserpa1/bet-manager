package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.BetRepository;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(validateBetReferences(betModel))
            if (validateBetNumbers(betModel.getNumbers()))
                return betRepository.save(betModel);
        return null;
    }

    public List<BetModel> getAllBetsBySortitionId(Integer id){
        return betRepository.getAllBySortitionId(id);
    }

    public BetModel createLittleSurprise(BetModel betModel){
        if(validateBetReferences(betModel)) {
            do {
                betModel.setNumbers(processingService.getLittleSurprise());
            } while (!validateBetNumbers(betModel.getNumbers()));

            return betRepository.save(betModel);
        }

        return null;
    }
    public Boolean validateBetReferences(BetModel betModel){
        List<UserModel> users = userRepository.findAll();
        List<SortitionModel> sortitions = sortitionRepository.findAll();

        boolean haveUser = false;
        boolean haveSortition = false;

        for (UserModel u : users)
            if(u.getId().equals(betModel.getIdUser()))
                haveUser = true;

        for (SortitionModel s : sortitions)
            if(s.getId().equals(betModel.getIdSortition()))
                haveSortition = true;

        return haveUser && haveSortition;
    }

    public Boolean validateBetNumbers(String numbers){
        String[] split = numbers.split(",");

        if (split.length != 5)
            return false;

        return processingService.validateNumbersIntegrity(split);
    }

    public List<BetModel> setWinners(List<BetModel> bets, SortitionModel sortitionModel){
        List<BetModel> winners = new ArrayList<>();

        for (BetModel b : bets){
            if(processingService.containsNumbers(sortitionModel.getNumbers(),b.getNumbers())) {
                b.setIsWinner(true);
                winners.add(b);
            }
        }

        return betRepository.saveAll(winners);
    }
}
