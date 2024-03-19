package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.BetDTO;
import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.BetService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BetControllerTest {

    BetModel bet;
    List<BetModel> bets;

    @Mock
    BetService betService;
    @InjectMocks
    BetController betController;

    @BeforeEach
    void setUp(){
        bet = new BetModel();
        bet.setId(1);
        bet.setIdUser(1);
        bet.setIdSortition(1);
        bet.setNumbers("1,2,3,4,5");
        bets = new ArrayList<>();
        bets.add(bet);

        Mockito.when(betService.create(bet)).thenReturn(bet);
        Mockito.when(betService.getAll()).thenReturn(bets);
        Mockito.when(betService.createLittleSurprise(bet)).thenReturn(bet);
    }

    @Test
    @Order(1)
    @DisplayName("BetController: get all bets correclty")
    void getAllCorrectly(){
        ResponseEntity<List<BetModel>> expected = new ResponseEntity<>(bets, HttpStatus.OK);
        var result = betController.getAll();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(2)
    @DisplayName("BetController: create bet correclty")
    void createNewBetCorrectly(){
        ResponseEntity<BetModel> expected = new ResponseEntity<>(bet, HttpStatus.CREATED);
        BetDTO betDTO = new BetDTO();
        BeanUtils.copyProperties(bet,betDTO);
        var result = betController.create(betDTO);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(3)
    @DisplayName("BetController: create little surprise bet correclty")
    void createLittleSurpriseBetCorrectly(){
        ResponseEntity<BetModel> expected = new ResponseEntity<>(bet, HttpStatus.CREATED);
        BetDTO betDTO = new BetDTO();
        BeanUtils.copyProperties(bet,betDTO);
        var result = betController.createLittleSurprise(betDTO);

        Assertions.assertEquals(expected, result);
    }
}
