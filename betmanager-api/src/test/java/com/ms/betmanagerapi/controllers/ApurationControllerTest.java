package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.BetService;
import com.ms.betmanagerapi.services.SortitionService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApurationControllerTest {

    @Mock
    SortitionService sortitionService;
    @Mock
    BetService betService;
    @InjectMocks
    ApurationController apurationController;


    @Test
    @Order(1)
    @DisplayName("BetController: get all bets correclty")
    void makeApurationCorrectly(){
        SortitionModel sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setIsFinished(false);

        BetModel bet = new BetModel();
        bet.setId(1);

        List<BetModel> bets = new ArrayList<>();
        bets.add(bet);


        Mockito.when(sortitionService.getById(1)).thenReturn(sortition);
        Mockito.when(betService.getAllBetsBySortitionId(1)).thenReturn(bets);

        SortitionModel sortitionMock = sortition;
        sortitionMock.setIsFinished(true);

        Mockito.when(sortitionService.makeApuration(sortition,bets)).thenReturn(sortitionMock);

        ResponseEntity<SortitionModel> expected = new ResponseEntity<>(sortitionMock, HttpStatus.OK);
        var result = apurationController.makeApuration(1);

        Assertions.assertEquals(expected, result);
    }


}
