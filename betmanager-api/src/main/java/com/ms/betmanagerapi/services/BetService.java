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

    public BetModel create(BetModel betModel){
        return betRepository.save(betModel);
    }

    public List<BetModel> getAll(){
        return betRepository.findAll();
    }

}
