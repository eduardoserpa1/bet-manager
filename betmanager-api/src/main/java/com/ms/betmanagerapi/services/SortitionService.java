package com.ms.betmanagerapi.services;

import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortitionService {

    @Autowired
    SortitionRepository sortitionRepository;

    public SortitionModel create(SortitionModel sortitionModel){
        return sortitionRepository.save(sortitionModel);
    }

    public List<SortitionModel> getAll(){
        return sortitionRepository.findAll();
    }
}
