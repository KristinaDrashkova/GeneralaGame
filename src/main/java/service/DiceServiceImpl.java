package service;

import java.util.ArrayList;
import java.util.List;

public class DiceServiceImpl implements DiceService {
    public List<Integer> roll(int diceCount) {
        if (diceCount < 0 || diceCount > 100) {
            throw new IllegalArgumentException();
        }
        List<Integer> diceValues = new ArrayList<>(diceCount);
        for (int i = 0; i < diceCount; i++) {
            int dieValue = (int) (Math.random() * 6) + 1;
            diceValues.add(dieValue);
        }
        return diceValues;
    }

    public List<Integer> roll() {
        return this.roll(1);
    }
}
