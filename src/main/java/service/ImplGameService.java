package service;

import java.io.BufferedReader;

import java.io.IOException;

public class ImplGameService implements GameService {
    private DiceService diceService;
    private BufferedReader bufferedReader;

    public ImplGameService(DiceService diceService, BufferedReader bufferedReader) {
        this.diceService = diceService;
        this.bufferedReader = bufferedReader;
    }

    public void startGame() throws IOException {
        System.out.println("Game started");
        diceService.rollingDiceIO(bufferedReader);
    }

    public void endGame() {
        System.out.println("Game ended");
    }

}
