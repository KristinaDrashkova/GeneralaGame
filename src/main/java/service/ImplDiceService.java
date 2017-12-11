package service;

import java.util.ArrayList;
import java.util.List;

public class ImplDiceService implements DiceService {
    public List<Integer> roll(int diceCount) {
        List<Integer> diceValues = new ArrayList<Integer>(diceCount);
        while (diceCount-- > 0) {
            int dieValue = (int) (Math.random() * 6) + 1;
            diceValues.add(dieValue);
        }
        return diceValues;
    }

    public List<Integer> roll() {
        return this.roll(1);
    }
}
