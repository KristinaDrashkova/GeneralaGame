package service;

import entities.Game;
import exceptions.ConsoleInputException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class GameServiceImplTest {

    @Test
    public void playShouldWorkCorrectlyWithNormalData() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        String numberOfPlayers = "2";
        String numberOfRounds = "2";
        String numberOfDice = "3";
        String firstPlayerName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(numberOfPlayers)
                .thenReturn(numberOfRounds)
                .thenReturn(numberOfDice)
                .thenReturn(firstPlayerName)
                .thenReturn(secondPlayerName);
        Mockito.when(mockedDiceService.roll(3))
                .thenReturn(Arrays.asList(2, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3))
                .thenReturn(Arrays.asList(2, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        Assert.assertEquals(Integer.parseInt(numberOfDice), game.getNumberOfDice());
        Assert.assertEquals(Integer.parseInt(numberOfRounds), game.getRounds());
//        Assert.assertEquals(firstPlayerName, game.getPlayers().get(0).getName());
//        int expectedFirstPlayerScore = 18;
//        Assert.assertEquals(expectedFirstPlayerScore, game.getPlayers().get(0).getResult());
//        Assert.assertEquals(secondPlayerName, game.getPlayers().get(1).getName());
//        int expectedSecondPlayerScore = 12;
//        Assert.assertEquals(expectedSecondPlayerScore, game.getPlayers().get(1).getResult());
    }

    @Test
    public void playShouldWorkCorrectlyWithWrongData() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        String wrongNumberOfPlayers = "-2";
        String numberOfPlayers = "2";
        String wrongNumberOfRounds = "101";
        String numberOfRounds = "2";
        String wrongNumberOfDice = "0";
        String numberOfDice = "3";
        String firstPlayerName = "A";
        String repeatName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(wrongNumberOfPlayers)
                .thenReturn(numberOfPlayers)
                .thenReturn(wrongNumberOfRounds)
                .thenReturn(numberOfRounds)
                .thenReturn(wrongNumberOfDice)
                .thenReturn(numberOfDice)
                .thenReturn(firstPlayerName)
                .thenReturn(repeatName)
                .thenReturn(secondPlayerName);
        Mockito.when(mockedDiceService.roll(3))
                .thenReturn(Arrays.asList(2, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3))
                .thenReturn(Arrays.asList(2, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        Assert.assertEquals(Integer.parseInt(numberOfDice), game.getNumberOfDice());
        Assert.assertEquals(Integer.parseInt(numberOfRounds), game.getRounds());
//        Assert.assertEquals(firstPlayerName, game.getPlayers().get(0).getName());
//        int expectedFirstPlayerScore = 18;
//        Assert.assertEquals(expectedFirstPlayerScore, game.getPlayers().get(0).getResult());
//        Assert.assertEquals(secondPlayerName, game.getPlayers().get(1).getName());
//        int expectedSecondPlayerScore = 12;
//        Assert.assertEquals(expectedSecondPlayerScore, game.getPlayers().get(1).getResult());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = ConsoleInputException.class)
    public void bufferedReadExceptionShouldThrowCustomException() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(mockedReader.readLine()).thenThrow(IOException.class);
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
    }
}