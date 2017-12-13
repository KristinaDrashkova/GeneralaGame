package service;

import exceptions.ConsoleInputException;

public interface GameService {
    /**
     * maximum count of rounds in the game
     */
    int ROUNDS_MAX_COUNT = 100;
    /**
     * maximum count of players in the game
     */
    int PLAYERS_MAX_COUNT = 10;
    /**
     * maximum count of dice in the game
     */
    int DICE_MAX_COUNT = 100;

    /**
     * method that starts Generala game
     *
     * @throws ConsoleInputException from working with BufferedReader
     */
    void play() throws ConsoleInputException;
}
