package service;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ImplGameServiceTest {
    private DiceService diceService;

    @Test
    public void playShouldWorkCorrectly() throws Exception {
        diceService = Mockito.mock(DiceService.class);
        GameService gameService = spy(new ImplGameService(diceService));
        gameService.play();
        verify(gameService, times(1)).play();
    }
}