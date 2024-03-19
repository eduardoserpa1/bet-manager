package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.UserDTO;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.UserRepository;
import com.ms.betmanagerapi.services.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    UserModel user;
    List<UserModel> users;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp(){
        user = new UserModel();
        user.setId(1);
        user.setName("teste");
        user.setCpf("11100011100");
        users = new ArrayList<>();
        users.add(user);

        Mockito.when(userService.getAll()).thenReturn(users);
        Mockito.when(userService.create(user)).thenReturn(user);
    }


    @Test
    @Order(1)
    @DisplayName("UserController: get all users correclty")
    void getAllUsersCorrectly(){
        ResponseEntity<List<UserModel>> expected = new ResponseEntity<>(users, HttpStatus.OK);
        var result = userController.getAll();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(2)
    @DisplayName("UserController: create a new user correclty")
    void createUserCorrectly(){
        ResponseEntity<UserModel> expected = new ResponseEntity<>(user, HttpStatus.CREATED);
        UserDTO udto = new UserDTO();
        BeanUtils.copyProperties(user,udto);

        var result = userController.create(udto);

        Assertions.assertEquals(expected, result);
    }
}
