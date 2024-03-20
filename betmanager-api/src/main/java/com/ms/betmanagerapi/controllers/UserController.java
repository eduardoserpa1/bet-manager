package com.ms.betmanagerapi.controllers;


import com.ms.betmanagerapi.dtos.UserDTO;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAll(){
        List<UserModel> allUsers = userService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserModel> create(@RequestBody UserDTO userDTO){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO,userModel);
        return new ResponseEntity<>(userService.create(userModel), HttpStatus.CREATED);
    }
}
