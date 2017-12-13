package service;

import entities.Game;
import entities.Player;

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

    public void play() throws IOException {
        game = initialize();
        for (int i = 0; i < game.getRounds(); i++) {
            for (Player player : game.getPlayers()) {
                int result = getResultSum(diceService.roll(game.getNumberOfDice()));
                addResultToPlayer(player, result);
            }
        }
        Collections.sort(game.getPlayers());
        System.out.println("Best players: ");
        int size = game.getPlayers().size() <= 3 ? game.getPlayers().size() : 3;
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("Player No %d [%s] scored: %d"
                    , i + 1, game.getPlayers().get(i).getName(), game.getPlayers().get(i).getResult()));
        }
    }

    private Game initialize() throws IOException {
        game = new Game();
        System.out.println("Enter number of players: ");
        int numberOfPlayers = getValidNumber(10);

        System.out.println("Enter number of rounds: ");
        int numberOfRounds = getValidNumber(100);

        System.out.println("Enter number of dice: ");
        int numberOfDice = getValidNumber(100);

        System.out.println(String.format("Enter %s names: ", numberOfPlayers));
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = bufferedReader.readLine();
            while (!isUniqueName(name, game)) {
                System.out.println("This name already exists, please enter different name");
                name = bufferedReader.readLine();
            }
            Player player = new Player(name);
            game.addPlayer(player);
        }
        game.setNumberOfDice(numberOfDice);
        game.setRounds(numberOfRounds);
        return game;
    }

    private void addResultToPlayer(Player player, int result) {
        int currentResult = player.getResult();
        int newResult = currentResult + result;
        player.setResult(newResult);
    }

    private int getResultSum(List<Integer> diceRolls) {
        int sum = 0;
        for (Integer diceRoll : diceRolls) {
            sum += diceRoll;
        }
        return sum;
    }

    private BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private int getValidNumber(int maxValue) throws IOException {
        String input = bufferedReader.readLine();
        while (!isValidNumber(input, maxValue)) {
            System.out.println("Please enter valid number");
            input = bufferedReader.readLine();
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
}
