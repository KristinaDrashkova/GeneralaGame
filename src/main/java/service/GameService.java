package service;

import java.io.IOException;

public interface GameService {

    /**
     * starts the game
     *
     * @throws IOException from working with BufferedReader
     */
    void play() throws IOException;
}
