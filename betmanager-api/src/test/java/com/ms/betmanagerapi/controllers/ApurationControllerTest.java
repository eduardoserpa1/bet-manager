package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
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

    @Test
    @Order(1)
    @DisplayName("BetController: set winners correclty")
    void setWinnersCorrectly(){
        UserModel user = new UserModel();
        user.setId(1);
        SortitionModel sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");

        BetModel bet = new BetModel();
        bet.setIdUser(1);
        bet.setIdSortition(1);
        bet.setNumbers("1,2,3,4,5");
        bet.setIsWinner(false);

        List<UserModel> users = new ArrayList<>();
        List<SortitionModel> sortitions = new ArrayList<>();
        users.add(user);
        sortitions.add(sortition);

        List<BetModel> bets = new ArrayList<>();
        bets.add(bet);

        List<BetModel> betsMock = new ArrayList<>();
        BetModel bet2 = new BetModel();
        bet2.setIdUser(1);
        bet2.setIdSortition(1);
        bet2.setNumbers("1,2,3,4,5");
        bet2.setIsWinner(true);
        betsMock.add(bet2);


        Mockito.when(sortitionService.getById(1)).thenReturn(sortition);
        Mockito.when(betService.getAllBetsBySortitionId(1)).thenReturn(bets);
        Mockito.when(betService.setWinners(bets,sortition)).thenReturn(betsMock);

        SortitionModel sortitionMock = sortition;
        sortitionMock.setIsFinished(true);

        Mockito.when(sortitionService.makeApuration(sortition,bets)).thenReturn(sortitionMock);

        ResponseEntity<List<BetModel>> expected = new ResponseEntity<>(betsMock, HttpStatus.OK);
        var result = apurationController.setWinners(1);

        Assertions.assertEquals(expected, result);
    }


}
