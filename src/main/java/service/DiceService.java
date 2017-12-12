package service;

import java.util.List;

public interface DiceService {


    /**
     * @return calls {@link #roll(int)} with param 1
     */
    List<Integer> roll();

    /**
     * Simulates throwing number of dice
     *
     * @param diceCount count of dice to roll
     * @return collection of dice values between 1 and 6 (including)
     */
    List<Integer> roll(int diceCount);

}
