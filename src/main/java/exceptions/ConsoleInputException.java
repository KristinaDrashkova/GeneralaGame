package exceptions;

import java.io.IOException;

public class ConsoleInputException extends IOException {
    public ConsoleInputException(String message) {
        super(message);
    }
}
