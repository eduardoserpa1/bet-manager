package com.ms.betmanagerapi.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDTOTest {
    @Test
    @DisplayName("UserDTO: Testing getters, setters and constructor.")
    public void userSetUp(){
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("teste");
        user.setCpf("11122233344");

        Assertions.assertInstanceOf(UserDTO.class, user);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("teste", user.getName());
        Assertions.assertEquals("11122233344", user.getCpf());
    }
}