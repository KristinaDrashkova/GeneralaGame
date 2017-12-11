package service;

import java.util.List;

public interface DiceService {

    List<Integer> roll();

    /*
    generates collection of integers between 1 and 6 (including)
    */
    List<Integer> roll(int diceCount);

}
