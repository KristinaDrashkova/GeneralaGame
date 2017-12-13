package exceptions;

import java.io.IOException;

/**
 * Wraps the IO Exception thrown by the BufferedReader
 */
public class ConsoleInputException extends IOException {
    public ConsoleInputException(String message) {
        super(message);
    }
}
