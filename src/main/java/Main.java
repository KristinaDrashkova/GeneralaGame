import service.DiceService;
import service.GameService;
import service.DiceServiceImpl;
import service.GameServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DiceService diceService = new DiceServiceImpl();
        GameService game = new GameServiceImpl(diceService);
        game.play();
    }
}
