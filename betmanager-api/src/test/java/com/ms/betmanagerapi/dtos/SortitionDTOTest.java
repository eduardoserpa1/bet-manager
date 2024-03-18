package com.ms.betmanagerapi.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortitionDTOTest {

    @Test
    @DisplayName("SortitionDTO: Testing getters, setters and constructor.")
    public void sortitionSetUp(){
        SortitionDTO sortition = new SortitionDTO();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");
        sortition.setIsFinished(false);

        Assertions.assertInstanceOf(SortitionDTO.class, sortition);
        Assertions.assertEquals(1, sortition.getId());
        Assertions.assertEquals("1,2,3,4,5", sortition.getNumbers());
        Assertions.assertFalse(sortition.getIsFinished());
    }
}
