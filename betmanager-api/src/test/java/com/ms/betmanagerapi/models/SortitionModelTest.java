package com.ms.betmanagerapi.models;

import com.ms.betmanagerapi.dtos.SortitionDTO;
import com.ms.betmanagerapi.models.SortitionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortitionModelTest {

    @Test
    @DisplayName("SortitionModel: Testing getters, setters and constructor.")
    public void sortitionModelSetUp(){
        SortitionModel sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");
        sortition.setIsFinished(false);

        Assertions.assertInstanceOf(SortitionModel.class, sortition);
        Assertions.assertEquals(1, sortition.getId());
        Assertions.assertEquals("1,2,3,4,5", sortition.getNumbers());
        Assertions.assertFalse(sortition.getIsFinished());
    }
}