import service.GameService;
import service.ImplGameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        GameService game = new ImplGameService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        game.startGame(reader);
        game.endGame();
    }
}
