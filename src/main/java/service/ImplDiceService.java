package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImplDiceService implements DiceService {
    public List<Integer> roll(int diceCount) {
        List<Integer> diceValues = new ArrayList<Integer>();
        while (diceCount-- > 0) {
            int dieValue = (int) (Math.random() * 6) + 1;
            diceValues.add(dieValue);
        }
        return diceValues;
    }

    public int getResultSum(List<Integer> diceRolls) {
        int sum = 0;
        for (Integer diceRoll : diceRolls) {
            sum += diceRoll;
        }

        return sum;
    }

    public void rollingDice(BufferedReader reader) throws IOException {
        char input = 'Y';
        while (input == 'Y') {
            int counter = 1;
            System.out.println("How many dice would you like to roll: ");
            int diceCount = Integer.parseInt(reader.readLine());
            List<Integer> diceRolls = roll(diceCount);
            for (Integer diceRoll : diceRolls) {
                System.out.println(String.format("%d roll: %d", counter, diceRoll));
                counter++;
            }
            System.out.println(String.format("Your total roll is: %d", getResultSum(diceRolls)));
            System.out.println("Would you like to roll again?{Y\\N}: ");
            input = reader.readLine().trim().toUpperCase().charAt(0);
            while (input != 'Y' && input != 'N') {
                System.out.println("Invalid input. Please enter Y or N!");
                input = reader.readLine().trim().toUpperCase().charAt(0);
            }
        }
    }
}
