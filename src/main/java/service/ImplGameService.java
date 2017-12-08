package service;

import java.io.BufferedReader;

import java.io.IOException;

public class ImplGameService implements GameService {
    public void startGame(BufferedReader reader) throws IOException {
        System.out.println("GameService started");
        DiceService diceService = new ImplDiceService();
        diceService.rollingDice(reader);
    }


    public void endGame() {
        System.out.println("GameService ended");
    }

}
