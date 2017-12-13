package service;

import entities.Game;
import entities.Player;
import exceptions.ConsoleInputException;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameServiceImpl implements GameService {
    private DiceService diceService;
    private BufferedReader bufferedReader;
    private Game game;

    public GameServiceImpl(DiceService diceService) {
        this.diceService = diceService;
        this.bufferedReader = getReader();
    }

    public void play() throws ConsoleInputException {
        game = initialize();
        for (int i = 0; i < game.getRounds(); i++) {
            for (Player player : game.getPlayers()) {
                int diceSum = getDiceSum(diceService.roll(game.getNumberOfDice()));
                updatePlayerScore(player, diceSum);
            }
        }
        Collections.sort(game.getPlayers());
        System.out.println("Best players: ");
        int winnersCount = game.getPlayers().size() <= 3 ? game.getPlayers().size() : 3;
        for (int playerIdx = 0; playerIdx < winnersCount; playerIdx++) {
            System.out.println(String.format("Player No %d [%s] scored: %d"
                    , playerIdx + 1, game.getPlayers().get(playerIdx).getName(), game.getPlayers().get(playerIdx).getResult()));
        }
    }

    private Game initialize() throws ConsoleInputException {
        game = new Game();
        System.out.println("Enter number of players: ");
        int numberOfPlayers = getValidNumber(PLAYERS_MAX_COUNT);

        System.out.println("Enter number of rounds: ");
        int numberOfRounds = getValidNumber(ROUNDS_MAX_COUNT);

        System.out.println("Enter number of dice: ");
        int numberOfDice = getValidNumber(DICE_MAX_COUNT);

        System.out.println(String.format("Enter %s names: ", numberOfPlayers));
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = readFromConsole();
            while (!isUniqueName(name, game)) {
                System.out.println("This name already exists, please enter different name");
                name = readFromConsole();
            }
            Player player = new Player(name);
            game.addPlayer(player);
        }
        game.setNumberOfDice(numberOfDice);
        game.setRounds(numberOfRounds);
        return game;
    }

    private void updatePlayerScore(Player player, int result) {
        int currentResult = player.getResult();
        int newResult = currentResult + result;
        player.setResult(newResult);
    }

    private int getDiceSum(List<Integer> diceRolls) {
        int sum = 0;
        for (Integer diceRoll : diceRolls) {
            sum += diceRoll;
        }
        return sum;
    }

    private BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private int getValidNumber(int maxValue) throws ConsoleInputException {
        String input = readFromConsole();
        while (!isValidNumber(input, maxValue)) {
            System.out.println(String.format("Please enter valid number between 1 and %d including", maxValue));
            input = readFromConsole();
        }

        return Integer.parseInt(input);
    }

    private boolean isValidNumber(String input, int maxValue) {
        try {
            int number = Integer.parseInt(input);
            if (number <= 0 || number > maxValue) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isUniqueName(String name, Game game) {
        return game.getPlayers().stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()).size() <= 0;
    }

    private String readFromConsole() throws ConsoleInputException {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new ConsoleInputException("There is a problem reading from the console");
        }
    }
}
