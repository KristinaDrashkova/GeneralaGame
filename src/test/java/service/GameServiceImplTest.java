package service;

import entities.Game;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GameServiceImplTest {

    @Test
    public void playShouldWorkCorrectly() throws Exception {
        DiceService mockedDiceService = Mockito.mock(DiceServiceImpl.class);
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(mockedReader.readLine())
                .thenReturn("2").thenReturn("2").thenReturn("3").thenReturn("A").thenReturn("B");
        Mockito.when(mockedDiceService.roll(3))
                .thenReturn(Arrays.asList(2, 3, 4)).thenReturn(Arrays.asList(1, 2, 3))
                .thenReturn(Arrays.asList(2, 3, 4)).thenReturn(Arrays.asList(1, 2, 3));
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
        Game game = (Game) Whitebox.getInternalState(gameService, "game");
        Assert.assertEquals(3, game.getNumberOfDice());
        Assert.assertEquals(2, game.getRounds());
        Assert.assertEquals("A", game.getPlayers().get(0).getName());
        Assert.assertEquals(18, game.getPlayers().get(0).getResult());
        Assert.assertEquals("B", game.getPlayers().get(1).getName());
        Assert.assertEquals(12, game.getPlayers().get(1).getResult());
    }
}