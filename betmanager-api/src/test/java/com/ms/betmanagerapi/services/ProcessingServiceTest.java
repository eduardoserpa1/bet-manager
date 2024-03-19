package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcessingServiceTest {

    @Autowired
    ProcessingService processingService;


    @BeforeEach
    void setUp(){

    }


    @Test
    @Order(1)
    @DisplayName("ProcessingService: validate if a sequence of numbers is valid: [1 - 50] and without duplicates")
    void validateIfSequenceOfNumbersIsValid(){
        String[] valid_numbers = {"1", "2", "3", "4", "5"};

        Boolean result = processingService.validateNumbersIntegrity(valid_numbers);

        Assertions.assertTrue(result);
    }

    @Test
    @Order(2)
    @DisplayName("ProcessingService: validate if a sequence of numbers is not valid with duplciates: [1 - 50] and without duplicates")
    void validateIfSequenceOfNumbersIsNotValidWithDuplicates(){
        String[] invalid_numbers = {"1", "2", "3", "4", "1"};

        Boolean result = processingService.validateNumbersIntegrity(invalid_numbers);

        Assertions.assertFalse(result);
    }

    @Test
    @Order(3)
    @DisplayName("ProcessingService: validate if a sequence of numbers is not valid out of range: [1 - 50] and without duplicates")
    void validateIfSequenceOfNumbersIsNotValidOutOfRange(){
        String[] invalid_numbers = {"1", "2", "3", "4", "51"};

        Boolean result = processingService.validateNumbersIntegrity(invalid_numbers);

        Assertions.assertFalse(result);
    }

    @Test
    @Order(4)
    @DisplayName("ProcessingService: compare if a sequence of five elements is contained in another sequence")
    void compareIfSequenceOfFiveNumbersIsContainedInAnotherSequence(){
        String s1 = "1,2,3,4,5";
        String s2 = "1,2,3,4,5,20,30,40";

        Boolean result = processingService.containsNumbers(s1,s2);

        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    @DisplayName("ProcessingService: compare if a sequence of five elements is not contained in another sequence")
    void compareIfSequenceOfFiveNumbersIsNotContainedInAnotherSequence(){
        String s1 = "1,2,3,4,49";
        String s2 = "1,2,3,4,5,20,30,40";

        Boolean result = processingService.containsNumbers(s1,s2);

        Assertions.assertFalse(result);
    }

    @Test
    @Order(6)
    @DisplayName("ProcessingService: validate if a sortition have a winner bet")
    void validateIfHaveSortitionWinnerBet(){
        SortitionModel sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");
        sortition.setIsFinished(false);
        List<BetModel> bets = new ArrayList<>();
        BetModel bet = new BetModel();
        bet.setNumbers("1,2,3,4,5");
        bets.add(bet);

        Boolean result = processingService.haveWinners(sortition,bets);

        Assertions.assertTrue(result);
    }

    @Test
    @Order(7)
    @DisplayName("ProcessingService: validate if a sortition doesn't have a winner bet")
    void validateIfDoesntHaveSortitionWinnerBet(){
        SortitionModel sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,10");
        sortition.setIsFinished(false);
        List<BetModel> bets = new ArrayList<>();
        BetModel bet = new BetModel();
        bet.setNumbers("1,2,3,4,5");
        bets.add(bet);

        Boolean result = processingService.haveWinners(sortition,bets);

        Assertions.assertFalse(result);
    }

    @Test
    @Order(8)
    @DisplayName("ProcessingService: validate if 'little surprise function' generates the sequence of numbers correctly")
    void validateIfLittleSurpriseGenerateCorrectly(){

        String ls = processingService.getLittleSurprise();

        Boolean result = processingService.validateNumbersIntegrity(ls.split(","));

        Assertions.assertTrue(result);
    }
}
