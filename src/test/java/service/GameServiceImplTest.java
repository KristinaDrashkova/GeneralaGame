package service;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.BufferedReader;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameServiceImplTest {

    @Test
    public void playShouldWorkCorrectly() throws Exception {
        DiceService mockedDiceService = spy(new DiceServiceImpl());
        BufferedReader mockedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(mockedReader.readLine()).thenReturn("Y").thenReturn("g");
        GameService gameService = new GameServiceImpl(mockedDiceService);
        Whitebox.setInternalState(gameService, "bufferedReader", mockedReader);
        gameService.play();
        verify(mockedDiceService, times(2)).roll(2);
    }
}