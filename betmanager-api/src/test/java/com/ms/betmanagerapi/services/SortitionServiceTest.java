package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    @DisplayName("SortitionService: create a sortition correctly")
    void createNewSortitionCorrectly(){
        Mockito.when(processingService.validateNumbersIntegrity(sortition.getNumbers().split(","))).thenReturn(true);
        Mockito.when(sortitionService.create(sortition)).thenReturn(sortition);

        var result = sortitionService.create(sortition);

        Assertions.assertEquals(sortition, result);
    }

    @Test
    @Order(2)
    @DisplayName("SortitionService: create a sortition wrong")
    void createNewSortitionWrong(){
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers())).thenReturn(false);

        var result = sortitionService.create(sortition);

        Assertions.assertNull(result);
    }

    @Test
    @Order(3)
    @DisplayName("SortitionService: get a sortition by id correctly")
    void getSortitionByIdCorrectly(){
        Mockito.when(sortitionService.getById(1)).thenReturn(sortition);

        var result = sortitionService.getById(1);

        Assertions.assertEquals(sortition, result);
    }

    @Test
    @Order(4)
    @DisplayName("SortitionService: get all sortitions correctly")
    void getAllSortitionsCorrectly(){
        List<SortitionModel> sortitions = new ArrayList<>();

        sortitions.add(sortition);

        Mockito.when(sortitionService.getAll()).thenReturn(sortitions);

        var result = sortitionService.getAll();

        Assertions.assertEquals(sortitions, result);
    }

    @Test
    @Order(5)
    @DisplayName("SortitionService: add a random number to a sortition correctly")
    void addRandomNumberCorrectly(){
        SortitionModel sortitionMock = sortition;

        Mockito.when(processingService.getRandomNumber()).thenReturn("10");
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers())).thenReturn(true);
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers() + ",10")).thenReturn(true);
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers() + ",10,10")).thenReturn(true);

        Mockito.when(sortitionService.addRandomNumber(sortitionMock)).thenReturn(sortition);


        var result = sortitionService.addRandomNumber(sortition);

        Assertions.assertEquals(sortition, result);
    }

    @Test
    @Order(6)
    @DisplayName("SortitionService: return the same sortition without modification when try to add a 31th number")
    void addRandomNumberWith30NumbersOnSortition(){
        SortitionModel sortitionMock = sortition;
        String numbers = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31";
        sortitionMock.setNumbers(numbers);

        Mockito.when(sortitionService.addRandomNumber(sortitionMock)).thenReturn(sortitionMock);

        var result = sortitionService.addRandomNumber(sortitionMock);

        Assertions.assertEquals(sortitionMock, result);
    }

    @Test
    @Order(7)
    @DisplayName("SortitionService: add a predefined number to a sortition correctly")
    void addPredefinedNumberCorrectly(){
        Mockito.when(sortitionRepository.save(sortition)).thenReturn(sortition);
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers() + ",10")).thenReturn(true);
        var result = sortitionService.addPredefinedNumber(sortition,10);

        Assertions.assertEquals(sortition, result);
    }

    @Test
    @Order(8)
    @DisplayName("SortitionService: add a worng predefined number to a sortition and return null")
    void addWrongPredefinedNumber(){

        String expected = sortition.getNumbers() + ",5";
        Mockito.when(sortitionRepository.save(sortition)).thenReturn(sortition);
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers() + ",5")).thenReturn(false);
        var result = sortitionService.addPredefinedNumber(sortition,5);

        Assertions.assertNull(result);
    }

    @Test
    @Order(9)
    @DisplayName("SortitionService: validate if sortition out of range [1 - 50] of numbers resturn false")
    void validateIfSortitionOutOfRangeReturnFalse(){

        var result1 = sortitionService.validateSortitionNumbers("1,2,3,4");
        var result2 = sortitionService.validateSortitionNumbers("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31");

        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
    }

    @Test
    @Order(10)
    @DisplayName("SortitionService: return random sortition correctly")
    void returnRandomSortitionCorrectly(){

        Mockito.when(sortitionRepository.save(sortition)).thenReturn(sortition);
        Mockito.when((processingService.getLittleSurprise())).thenReturn("1,2,3,4,5");
        Mockito.when(sortitionService.validateSortitionNumbers(sortition.getNumbers())).thenReturn(true);

        var result = sortitionService.createRandom(sortition);

        Assertions.assertEquals(sortition, result);
    }

    @Test
    @Order(11)
    @DisplayName("SortitionService: set true to sortition with apuration correctly")
    void setTrueToSortitionWithApurationCorrectly(){
        List<BetModel> bets = new ArrayList<>();
        BetModel bet1 = new BetModel();
        bet1.setNumbers("1,2,3,4,5");
        bets.add(bet1);

        Mockito.when(sortitionRepository.save(sortition)).thenReturn(sortition);
        Mockito.when(processingService.haveWinners(sortition,bets)).thenReturn(true);

        var result = sortitionService.makeApuration(sortition,bets);

        Assertions.assertTrue(result.getIsFinished());
    }




}
