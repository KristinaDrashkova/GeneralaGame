package service;

import entities.Game;
import entities.Player;
import exceptions.ConsoleInputException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameServiceImplTest {
    private DiceService mockedDiceService;
    private BufferedReader mockedReader;
    private GameService gameService;

    @Before
    public void initialize() {
        mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        mockedReader = Mockito.mock(BufferedReader.class);
        gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
    }

    @Test
    public void playShouldWorkCorrectlyWithNormalData() throws IOException {
        initializeBufferedReaderWithNormalData();
        initializeDiceServiceWithAllCombinationsExceptGenerala();
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int expectedFirstPlayerScore = 234;
        Player winner = game.getWinner();
        List<Combination> combinations = game.getPlayers().get(winner);
        Assert.assertEquals(expectedFirstPlayerScore, winner.getResult());
        Assert.assertEquals(1, combinations.size());
        Assert.assertEquals(combinations.get(0), Combination.GENERALA);
    }

    @Test
    public void initializeShouldWorkCorrectlyWithWrongData() throws IOException {
        initializeBufferedReaderWithWrongData();
        initializeDiceServiceWithAllCombinationsExceptGenerala();
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int numberOfPlayers = game.getPlayers().size();
        String playerOneName = game.getPlayers().keySet().toArray(new Player[0])[0].getName();
        String playerTwoName = game.getPlayers().keySet().toArray(new Player[0])[1].getName();
        Assert.assertEquals(2, numberOfPlayers);
        Assert.assertEquals("A", playerOneName);
        Assert.assertEquals("B", playerTwoName);
    }

    @Test
    public void gameShouldEndWithGeneralaScore() throws IOException {
        initializeBufferedReaderWithNormalData();
        Mockito.when(mockedDiceService.roll(5))
                .thenReturn(Arrays.asList(2, 2, 2, 2, 2));
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int expectedFirstPlayerScore = 60;
        Player winner = game.getWinner();
        Assert.assertEquals(expectedFirstPlayerScore, winner.getResult());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = ConsoleInputException.class)
    public void bufferedReadExceptionShouldThrowCustomException() throws IOException {
        Mockito.when(mockedReader.readLine()).thenThrow(IOException.class);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
    }

    @Test
    public void gameInitializeShouldWorkCorrectlyWithNormalData() throws IOException {
        initializeBufferedReaderWithNormalData();
        initializeDiceServiceWithAllCombinationsExceptGenerala();
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        int numberOfPlayers = game.getPlayers().size();
        String playerOneName = game.getPlayers().keySet().toArray(new Player[0])[0].getName();
        String playerTwoName = game.getPlayers().keySet().toArray(new Player[0])[1].getName();
        Assert.assertEquals(2, numberOfPlayers);
        Assert.assertEquals("A", playerOneName);
        Assert.assertEquals("B", playerTwoName);
    }

    private void initializeBufferedReaderWithNormalData() throws IOException {
        String numberOfPlayers = "2";
        String firstPlayerName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(numberOfPlayers)
                .thenReturn(firstPlayerName)
                .thenReturn(secondPlayerName);
    }

    private void initializeBufferedReaderWithWrongData() throws IOException {
        String wrongNumberOfPlayers = "-2";
        String invalidNumberOfPlayers = "ABC";
        String numberOfPlayers = "2";
        String firstPlayerName = "A";
        String repeatName = "A";
        String secondPlayerName = "B";
        Mockito.when(mockedReader.readLine())
                .thenReturn(wrongNumberOfPlayers)
                .thenReturn(invalidNumberOfPlayers)
                .thenReturn(numberOfPlayers)
                .thenReturn(firstPlayerName)
                .thenReturn(repeatName)
                .thenReturn(secondPlayerName);
    }

    private void initializeDiceServiceWithAllCombinationsExceptGenerala() {
        Mockito.when(mockedDiceService.roll(5))
                .thenReturn(Arrays.asList(5, 5, 5, 5, 1)) //adds 60 points(four of a kind)
                .thenReturn(Arrays.asList(4, 4, 4, 4, 5)) //adds 56 points(four of a kind)
                .thenReturn(Arrays.asList(5, 5, 5, 5, 1)) //adds 35 points(triple)
                .thenReturn(Arrays.asList(1, 3, 3, 3, 2)) //adds 29 points(triple)
                .thenReturn(Arrays.asList(3, 4, 3, 3, 3)) //adds 27 points(double pair)
                .thenReturn(Arrays.asList(2, 2, 2, 2, 1)) //adds 23 points(double pair)
                .thenReturn(Arrays.asList(1, 2, 3, 5, 6)) //nothing
                .thenReturn(Arrays.asList(2, 3, 4, 6, 1)) //nothing
                .thenReturn(Arrays.asList(2, 3, 4, 5, 6)) //adds 50 points(straight)
                .thenReturn(Arrays.asList(1, 2, 3, 4, 5)) //adds 45 points(straight)
                .thenReturn(Arrays.asList(5, 5, 5, 1, 1)) //adds 42 points(full house)
                .thenReturn(Arrays.asList(5, 2, 2, 2, 5)) //adds 41 points(full house)
                .thenReturn(Arrays.asList(5, 2, 2, 4, 5)) //adds 20 points(pair)
                .thenReturn(Arrays.asList(1, 1, 2, 2, 2)); //adds 14 points(pair)
    }
}