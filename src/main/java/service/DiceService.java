package service;

import java.util.List;

public interface DiceService {


    /**
     * @return calls roll() with param 1
     */
    List<Integer> roll();

    /**
     * @param diceCount count of dice to roll
     * @return collection of integers between 1 and 6 (including)
     */
    List<Integer> roll(int diceCount);

}
