package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortitionServiceTest {

    SortitionModel sortition;

    @Mock
    SortitionRepository sortitionRepository;
    @Mock
    ProcessingService processingService;

    @InjectMocks
    SortitionService sortitionService;



    @BeforeEach
    void setUp(){
        sortition = new SortitionModel();

        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");
        sortition.setIsFinished(false);
    }

    @Test
    @DisplayName("SortitionService: create a sortition correctly")
    void testIfCreateNewSortitionCorrectly(){
        Mockito.when(processingService.validadeNumbersIntegrity(sortition.getNumbers().split(","))).thenReturn(true);
        Mockito.when(sortitionService.create(sortition)).thenReturn(sortition);

        var result = sortitionService.create(sortition);

        Assertions.assertEquals(result, sortition);
    }

    @Test
    @DisplayName("SortitionService: create a sortition wrong")
    void testIfCreateNewSortitionWrong(){
        Mockito.when(sortitionService.validadeNumbers(sortition.getNumbers())).thenReturn(true);
        Mockito.when(sortitionService.create(sortition)).thenReturn(null);

        var result = sortitionService.create(sortition);

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("SortitionService: add a random number to a sortition correctly")
    void testIfAddRandomNumberCorrectly(){
        SortitionModel sortitionMock = sortition;

        Mockito.when(processingService.getRandomNumber()).thenReturn("10");
        Mockito.when(sortitionService.validadeNumbers(sortition.getNumbers())).thenReturn(true);
        Mockito.when(sortitionService.validadeNumbers(sortition.getNumbers() + ",10")).thenReturn(true);

        var result = sortitionService.addRandomNumber(sortition);

        Assertions.assertEquals(result, sortition);
    }

}
