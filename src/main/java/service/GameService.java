package service;

import exceptions.ConsoleInputException;

import java.io.IOException;

public interface GameService {
    /**
     * maximum count of rounds that the game may have
     */
    int ROUNDS_MAX_COUNT = 100;
    /**
     * maximum count of players that the game may have
     */
    int PLAYERS_MAX_COUNT = 10;
    /**
     * maximum count of dice that the game may have
     */
    int DICE_MAX_COUNT = 100;

    /**
     * method that starts Generala game
     *
     * @throws ConsoleInputException from working with BufferedReader
     */
    void play() throws ConsoleInputException;
}
