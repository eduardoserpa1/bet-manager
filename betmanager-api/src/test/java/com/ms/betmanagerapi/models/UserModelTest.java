package com.ms.betmanagerapi.models;

import com.ms.betmanagerapi.models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserModelTest {

    @Test
    @DisplayName("UserModel: Testing getters, setters and constructor.")
    public void userModelSetUp(){
        UserModel user = new UserModel();
        user.setId(1);
        user.setName("teste");
        user.setCpf("11122233344");

        Assertions.assertInstanceOf(UserModel.class, user);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("teste", user.getName());
        Assertions.assertEquals("11122233344", user.getCpf());
    }
}
