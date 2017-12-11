import service.DiceService;
import service.GameService;
import service.ImplDiceService;
import service.ImplGameService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DiceService diceService = new ImplDiceService();
        GameService game = new ImplGameService(diceService);
        game.play();
    }
}
