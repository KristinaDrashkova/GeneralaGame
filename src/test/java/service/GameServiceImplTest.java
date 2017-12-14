package service;

import entities.Game;
import exceptions.ConsoleInputException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServiceImplTest {

    @Test
    public void playShouldWorkCorrectlyWithNormalData() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        String numberOfPlayers = "2";
        String firstPlayerName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(numberOfPlayers)
                .thenReturn(firstPlayerName)
                .thenReturn(secondPlayerName);
        Mockito.when(mockedDiceService.roll(5))
                .thenReturn(Arrays.asList(2, 3, 4, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3, 4, 5))
                .thenReturn(Arrays.asList(2, 3, 4, 4, 4))
                .thenReturn(Arrays.asList(1, 2, 3, 3, 2));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader" , mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int expectedFirstPlayerScore = 77;
        Assert.assertEquals(expectedFirstPlayerScore, (new ArrayList<>(game.getPlayers().keySet())).get(0).getResult());
    }

    @Test
    public void playShouldWorkCorrectlyWithWrongData() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        String wrongNumberOfPlayers = "-2";
        String numberOfPlayers = "2";
        String firstPlayerName = "A";
        String repeatName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(wrongNumberOfPlayers)
                .thenReturn(numberOfPlayers)
                .thenReturn(firstPlayerName)
                .thenReturn(repeatName)
                .thenReturn(secondPlayerName);
        Mockito.when(mockedDiceService.roll(5))
                .thenReturn(Arrays.asList(2, 3, 4, 3, 4))
                .thenReturn(Arrays.asList(1, 2, 3, 4, 5))
                .thenReturn(Arrays.asList(2, 3, 4, 4, 4))
                .thenReturn(Arrays.asList(1, 2, 3, 3, 2));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader" , mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int expectedFirstPlayerScore = 77;
        Assert.assertEquals(expectedFirstPlayerScore, (new ArrayList<>(game.getPlayers().keySet())).get(0).getResult());
    }

    @Test
    public void gameShouldEndWithGeneralaScore() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        String wrongNumberOfPlayers = "-2";
        String numberOfPlayers = "2";
        String firstPlayerName = "A";
        String repeatName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(wrongNumberOfPlayers)
                .thenReturn(numberOfPlayers)
                .thenReturn(firstPlayerName)
                .thenReturn(repeatName)
                .thenReturn(secondPlayerName);
        Mockito.when(mockedDiceService.roll(5))
                .thenReturn(Arrays.asList(2, 2, 2, 2, 2));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader" , mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int expectedFirstPlayerScore = 60;
        Assert.assertEquals(expectedFirstPlayerScore, (new ArrayList<>(game.getPlayers().keySet())).get(0).getResult());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = ConsoleInputException.class)
    public void bufferedReadExceptionShouldThrowCustomException() throws IOException {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(mockedReader.readLine()).thenThrow(IOException.class);
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader" , mockedReader);
        gameService.play();
    }
}