package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.BetRepository;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SortitionRepository sortitionRepository;
    @Autowired
    BetRepository betRepository;


    public Boolean validadeBetReferences(BetModel betModel){
        Optional<UserModel> userModel = userRepository.findById(betModel.getIdUser());
        Optional<SortitionModel> sortitionModel = sortitionRepository.findById(betModel.getIdSortition());

        return userModel.isPresent() && sortitionModel.isPresent();
    }

    public Boolean validadeNumbers(String numbers){
        String[] split = numbers.split(",");

        for(int i = 0; i < split.length; i++){
            Integer numberInteger = Integer.parseInt(split[i]);

            if(numberInteger < 1 || numberInteger > 50)
                return false;

            for (int j = i+1; j < split.length; j++)
                if(split[i].equals(split[j]))
                    return false;
        }

        return true;
    }

}
