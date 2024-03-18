package com.ms.betmanagerapi.models;

import com.ms.betmanagerapi.dtos.BetDTO;
import com.ms.betmanagerapi.models.BetModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BetModelTest {

    @Test
    @DisplayName("BetModel: Testing getters, setters and constructor.")
    public void betSetUp(){
        BetModel bet = new BetModel();
        bet.setId(1);
        bet.setIdUser(1);
        bet.setIdSortition(1);
        bet.setNumbers("1,2,3,4,5");

        Assertions.assertInstanceOf(BetModel.class, bet);
        Assertions.assertEquals(1, bet.getId());
        Assertions.assertEquals(1, bet.getIdUser());
        Assertions.assertEquals(1, bet.getIdSortition());
        Assertions.assertEquals("1,2,3,4,5", bet.getNumbers());
    }
}
