package service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GameServiceImpl implements GameService {
    private DiceService diceService;
    private BufferedReader bufferedReader;

    public GameServiceImpl(DiceService diceService) {
        this.diceService = diceService;
        this.bufferedReader = getReader();
    }

    public void play() throws IOException {
        startGame();
    }

    private void startGame() throws IOException {
        System.out.println("Game started");
        rollingDiceIO();
    }

    private void rollingDiceIO() throws IOException {
        char input;
        do {
            int counter = 1;
            List<Integer> diceRolls = diceService.roll(2);
            for (Integer diceRoll : diceRolls) {
                System.out.println(String.format("%d roll: %d", counter++, diceRoll));
            }
            System.out.println(String.format("Your total roll is: %d", getResultSum(diceRolls)));
            System.out.println("Would you like to roll again?{Y\\N}: ");
            input = bufferedReader.readLine().trim().toUpperCase().charAt(0);
        }
        while (input == 'Y');
    }

    private int getResultSum(List<Integer> diceRolls) {
        int sum = 0;
        for (Integer diceRoll : diceRolls) {
            sum += diceRoll;
        }
        return sum;
    }

    private BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
