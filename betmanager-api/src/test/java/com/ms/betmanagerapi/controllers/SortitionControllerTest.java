package com.ms.betmanagerapi.controllers;

import com.ms.betmanagerapi.dtos.SortitionDTO;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.services.SortitionService;
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
public class SortitionControllerTest {

    SortitionModel sortition;
    List<SortitionModel> sortitions;

    @Mock
    SortitionService sortitionService;

    @InjectMocks
    SortitionController sortitionController;

    @BeforeEach
    void setUp(){
        sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");
        sortition.setIsFinished(false);
        sortitions = new ArrayList<>();
        sortitions.add(sortition);


        Mockito.when(sortitionService.create(sortition)).thenReturn(sortition);
        Mockito.when(sortitionService.getById(1)).thenReturn(sortition);
        Mockito.when(sortitionService.getAll()).thenReturn(sortitions);
        Mockito.when(sortitionService.createRandom(sortition)).thenReturn(sortition);
    }

    @Test
    @Order(1)
    @DisplayName("SortitionController: get all sortitions correclty")
    void getAllCorrectly(){
        ResponseEntity<List<SortitionModel>> expected = new ResponseEntity<>(sortitions, HttpStatus.OK);
        var result = sortitionController.getAll();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(2)
    @DisplayName("SortitionController: create a predefined sortition correclty")
    void createPredefinedSortitionCorrectly(){
        ResponseEntity<SortitionModel> expected = new ResponseEntity<>(sortition, HttpStatus.CREATED);
        SortitionDTO sortitionDTO = new SortitionDTO();
        BeanUtils.copyProperties(sortition,sortitionDTO);
        var result = sortitionController.createPredefined(sortitionDTO);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(3)
    @DisplayName("SortitionController: create a random sortition correclty")
    void createRandomSortitionCorrectly(){
        ResponseEntity<SortitionModel> expected = new ResponseEntity<>(sortition, HttpStatus.CREATED);
        SortitionDTO sortitionDTO = new SortitionDTO();
        BeanUtils.copyProperties(sortition,sortitionDTO);
        var result = sortitionController.createRandom(sortitionDTO);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(4)
    @DisplayName("SortitionController: add predefined number to a sortition correclty")
    void addPredefinedNumberCorrectly(){
        SortitionModel sortitionMock = sortition;
        sortitionMock.setNumbers(sortition.getNumbers() + ",10");

        ResponseEntity<SortitionModel> expected = new ResponseEntity<>(sortitionMock, HttpStatus.OK);

        SortitionDTO sortitionDTO = new SortitionDTO();
        BeanUtils.copyProperties(sortition,sortitionDTO);

        Mockito.when(sortitionService.addPredefinedNumber(sortition,10)).thenReturn(sortitionMock);

        var result = sortitionController.addPredefinedNumberById(1,10);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @Order(5)
    @DisplayName("SortitionController: add random number to a sortition correclty")
    void addRandomNumberCorrectly(){
        SortitionModel sortitionMock = sortition;
        sortition.setNumbers(sortition.getNumbers() + ",10");

        ResponseEntity<SortitionModel> expected = new ResponseEntity<>(sortition, HttpStatus.OK);

        SortitionDTO sortitionDTO = new SortitionDTO();
        BeanUtils.copyProperties(sortitionMock,sortitionDTO);

        Mockito.when(sortitionService.addRandomNumber(sortition)).thenReturn(sortitionMock);

        var result = sortitionController.addRandomNumberById(1);

        Assertions.assertEquals(expected, result);
    }
}
