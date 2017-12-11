package service;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ImplGameServiceTest {
    @Test
    public void startGameShouldCallStartGameOnce() throws Exception {
        GameService mockedGameService = Mockito.mock(ImplGameService.class);
        mockedGameService.startGame();
        Mockito.verify(mockedGameService, Mockito.times(1)).startGame();
    }
}