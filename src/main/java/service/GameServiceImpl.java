package service;

import entities.Game;
import entities.Player;
import exceptions.ConsoleInputException;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GameServiceImpl implements GameService {
    private static final int NUMBER_OF_DICE = 5;
    private DiceService diceService;
    private BufferedReader bufferedReader;
    private Game game;

    public GameServiceImpl(DiceService diceService) {
        this.diceService = diceService;
        this.bufferedReader = getReader();
    }

    public void play() throws ConsoleInputException {
        game = initialize();
        Player winner = null;
        for (int i = 0; i < game.getRounds(); i++) {
            if (winner == null) {
                for (Player player : game.getPlayers().keySet()) {
                    List<Integer> dice = diceService.roll(5);
                    System.out.println(String.format("Player %s has thrown %s", player.getName(), dice));
                    int maxCombinationResult = 0;
                    Combination combinationThrown = null;
                    for (Combination combination : game.getPlayers().get(player)) {
                        int combinationResult = combination.calculate(dice);
                        if (combinationResult > maxCombinationResult) {
                            maxCombinationResult = combinationResult;
                            combinationThrown = combination;
                        }
                    }
                    if (maxCombinationResult != 0) {
                        if (combinationThrown.equals(Combination.GENERALA)) {
                            winner = player;
                            updatePlayerScore(player, maxCombinationResult, combinationThrown);
                            break;
                        }
                        System.out.println(String.format("%s chose %s combination and added %d points to his/her score"
                                , player.getName(), combinationThrown.toString(), maxCombinationResult));
                        updatePlayerScore(player, maxCombinationResult, combinationThrown);
                    }
                }
            }
        }

        if (winner == null) {
            winner = game.getWinner();
        }
        System.out.println(String.format("The winner is: %s with score: %d", winner.getName(), winner.getResult()));
    }

    private Game initialize() throws ConsoleInputException {
        game = new Game();
        System.out.println("Enter number of players: ");
        int numberOfPlayers = getValidNumber(PLAYERS_MAX_COUNT);
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
        game.setNumberOfDice(NUMBER_OF_DICE);
        game.setRounds(Combination.values().length);
        return game;
    }

    private void updatePlayerScore(Player player, int result, Combination combination) {
        int currentResult = player.getResult();
        int newResult = currentResult + result;
        player.setResult(newResult);
        game.getPlayers().get(player).remove(combination);
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
        for (Player player : game.getPlayers().keySet()) {
            if (player.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private String readFromConsole() throws ConsoleInputException {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new ConsoleInputException("There is a problem reading from the console");
        }
    }
}
