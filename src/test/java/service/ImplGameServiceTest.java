package service;

import org.junit.Test;
import org.mockito.Mockito;

public class ImplGameServiceTest {
    @Test
    public void startGameShouldCallStartGameOnce() throws Exception {
        GameService mockedGameService = Mockito.mock(ImplGameService.class);
        mockedGameService.startGame();
        Mockito.verify(mockedGameService, Mockito.times(1)).startGame();
    }
}