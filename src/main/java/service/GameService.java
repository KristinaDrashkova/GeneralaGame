package service;

import entities.Game;

import java.io.IOException;

public interface GameService {

    /**
     * method that starts Generala game
     *
     * @throws IOException from working with BufferedReader
     */
    void play() throws IOException;


    /**
     * method that receives and distributes the input information for the game
     *
     * @return valid instance of a game
     * @throws IOException from working with BufferedReader
     */
    Game initialize() throws IOException;
}
