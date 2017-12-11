package service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ImplGameService implements GameService {
    private DiceService diceService;

    public ImplGameService(DiceService diceService) {
        this.diceService = diceService;
    }

    public void play() throws IOException {
        startGame();
    }

    private void startGame() throws IOException {
        System.out.println("Game started");
        rollingDiceIO(getReader());
    }

    private void rollingDiceIO(BufferedReader reader) throws IOException {
        char input = 'Y';
        while (input == 'Y') {
            int counter = 1;
            List<Integer> diceRolls = diceService.roll(2);
            for (Integer diceRoll : diceRolls) {
                System.out.println(String.format("%d roll: %d", counter, diceRoll));
                counter++;
            }
            System.out.println(String.format("Your total roll is: %d", getResultSum(diceRolls)));
//            System.out.println("Would you like to roll again?{Y\\N}: ");
//            input = reader.readLine().trim().toUpperCase().charAt(0);
//            while (input != 'Y' && input != 'N') {
//                System.out.println("Invalid input. Please enter Y or N!");
//                input = reader.readLine().trim().toUpperCase().charAt(0);
//            }
            input = 'N';
        }
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
