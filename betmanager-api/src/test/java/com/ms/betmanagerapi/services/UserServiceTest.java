package com.ms.betmanagerapi.services;

import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    UserModel user;

    List<UserModel> users = new ArrayList<>();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp(){
        user = new UserModel();
        user.setId(1);
        user.setName("user 1");
        user.setCpf("11100011100");

        UserModel user2 = new UserModel();
        user2.setId(2);
        user2.setName("user 2");
        user2.setCpf("22200022200");

        users.add(user);
        users.add(user2);
    }

    @Test
    @DisplayName("UserService: creates a UserModel instance correctly")
    void testIfCreateUserModelInstanceCorrectly(){
        Mockito.when(userService.create(user)).thenReturn(user);
        var result = userService.create(user);

        Assertions.assertEquals(user, result);
    }

    @Test
    @DisplayName("UserService: return all the instances on repository")
    void testIfReturnAllInstancesOnRepository(){
        userService.create(users.get(0));
        userService.create(users.get(1));

        Mockito.when(userService.getAll()).thenReturn(users);

        var result = userService.getAll();

        Assertions.assertEquals(users, result);
    }

}
