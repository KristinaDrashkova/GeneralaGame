import exceptions.ConsoleInputException;
import service.DiceService;
import service.GameService;
import service.DiceServiceImpl;
import service.GameServiceImpl;

public class Main {
    public static void main(String[] args) throws ConsoleInputException {
        DiceService diceService = new DiceServiceImpl();
        GameService game = new GameServiceImpl(diceService);
        game.play();
    }
}
