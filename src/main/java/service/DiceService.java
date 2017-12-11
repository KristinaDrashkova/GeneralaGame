package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface DiceService {
    List<Integer> roll(int diceCount);

    int getResultSum(List<Integer> diceRolls);

    void rollingDiceIO(BufferedReader reader) throws IOException;
}
