package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface DiceService {
    List<Integer> roll(int diceCount);
    int getResultSum(List<Integer> diceRolls);
    void rollingDice(BufferedReader reader) throws IOException;
}
