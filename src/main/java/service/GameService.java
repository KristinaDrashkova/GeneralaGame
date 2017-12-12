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

    Game initialize() throws IOException;
}
