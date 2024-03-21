package com.ms.betmanagerapi.services;


import com.ms.betmanagerapi.models.BetModel;
import com.ms.betmanagerapi.models.SortitionModel;
import com.ms.betmanagerapi.models.UserModel;
import com.ms.betmanagerapi.repositories.BetRepository;
import com.ms.betmanagerapi.repositories.SortitionRepository;
import com.ms.betmanagerapi.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BetServiceTest {

    UserModel user;
    SortitionModel sortition;
    BetModel bet;

    @Mock
    BetRepository betRepository;
    @Mock
    SortitionRepository sortitionRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProcessingService processingService;

    @InjectMocks
    BetService betService;

    @BeforeEach
    void setUp(){
        user = new UserModel();
        user.setId(1);
        sortition = new SortitionModel();
        sortition.setId(1);
        sortition.setNumbers("1,2,3,4,5");

        bet = new BetModel();
        bet.setIdUser(1);
        bet.setIdSortition(1);
        bet.setNumbers("1,2,3,4,5");
        bet.setIsWinner(false);

        List<UserModel> users = new ArrayList<>();
        List<SortitionModel> sortitions = new ArrayList<>();
        users.add(user);
        sortitions.add(sortition);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(sortitionRepository.findAll()).thenReturn(sortitions);

        Mockito.when(processingService.getLittleSurprise()).thenReturn("1,2,3,4,5");

        Mockito.when(processingService.validateNumbersIntegrity(bet.getNumbers().split(","))).thenReturn(true);

        Mockito.when(betRepository.save(bet)).thenReturn(bet);
    }


    @Test
    @Order(1)
    @DisplayName("BetService: create a new bet correctly")
    void createNewBetCorrectly(){

        var result = betService.create(bet);

        Assertions.assertEquals(bet, result);
    }

    @Test
    @Order(2)
    @DisplayName("BetService: return null when trying to create a bet with invalid parameters")
    void returnNullWhenCreateInvalidBet(){
        bet.setIdUser(100);
        bet.setIdSortition(100);
        bet.setNumbers("1,1,1,1,1");

        var result = betService.create(bet);

        Assertions.assertNull(result);
    }

    @Test
    @Order(3)
    @DisplayName("BetService: return a list with all bets correctly")
    void getAllBetsCorrectly(){
        List<BetModel> bets = new ArrayList<>();
        bets.add(bet);

        Mockito.when(betRepository.findAll()).thenReturn(bets);

        var result = betService.getAll();

        Assertions.assertEquals(bets, result);
    }

    @Test
    @Order(4)
    @DisplayName("BetService: return true when validate bet numbers entry is correct")
    void returnTrueWhenValidateBetNumbersEntryCorrect(){
        Mockito.when(processingService.validateNumbersIntegrity(bet.getNumbers().split(","))).thenReturn(true);
        var result = betService.validateBetNumbers(bet.getNumbers());

        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    @DisplayName("BetService: return false when validate bet numbers entry is wrong")
    void returnFalseWhenValidateBetNumbersEntryWrong(){
        bet.setNumbers(bet.getNumbers() + ",6");
        Mockito.when(processingService.validateNumbersIntegrity(bet.getNumbers().split(","))).thenReturn(true);
        var result = betService.validateBetNumbers(bet.getNumbers());

        Assertions.assertFalse(result);
    }

    @Test
    @Order(6)
    @DisplayName("BetService: return true if validates the references correctly")
    void returnTrueIfValidateReferencesCorrectly(){
        var result = betService.validateBetReferences(bet);

        Assertions.assertTrue(result);
    }

    @Test
    @Order(7)
    @DisplayName("BetService: return BetModel correct if create a bet with random numbers (little surprise)")
    void returnCorrectBetWithRandomNumbers(){
        var result = betService.createLittleSurprise(bet);

        Assertions.assertEquals(bet, result);
    }

    @Test
    @Order(8)
    @DisplayName("BetService: return null if trying create a bet with random numbers using bad entry (little surprise)")
    void returnNullIfTryingCreateRandomBetWithBadEntry(){
        bet.setIdSortition(2);
        bet.setIdUser(2);
        var result = betService.createLittleSurprise(bet);

        Assertions.assertNull(result);
    }

    @Test
    @Order(9)
    @DisplayName("BetService: get all bets by the sortition id")
    void returnAllBetsBySortitionId(){
        List<BetModel> bets = new ArrayList<>();
        bets.add(bet);

        Mockito.when(betService.getAllBetsBySortitionId(1)).thenReturn(bets);

        var result = betService.getAllBetsBySortitionId(bet.getIdSortition());

        Assertions.assertEquals(bets, result);
    }

    @Test
    @Order(10)
    @DisplayName("BetService: set winners set and return correctly")
    void setWinnersSetAndReturnCorrectly(){
        List<BetModel> bets = new ArrayList<>();
        bets.add(bet);

        List<BetModel> betsMock = new ArrayList<>();
        BetModel bet2 = new BetModel();
        bet2.setIdUser(1);
        bet2.setIdSortition(1);
        bet2.setNumbers("1,2,3,4,5");
        bet2.setIsWinner(true);
        bets.add(bet2);

        var result = betService.setWinners(bets, sortition);

        Assertions.assertEquals(betsMock, result);
    }

}
