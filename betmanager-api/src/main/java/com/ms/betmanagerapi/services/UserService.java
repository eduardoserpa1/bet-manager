package com.ms.betmanagerapi.services;

import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel create(UserModel userModel){
        return userRepository.save(userModel);
    }

    public List<UserModel> getAll(){
        return userRepository.findAll();
    }
}
