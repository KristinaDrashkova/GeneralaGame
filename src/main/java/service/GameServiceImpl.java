package service;

import entities.Game;
import entities.Player;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameServiceImpl implements GameService {
    private DiceService diceService;
    private BufferedReader bufferedReader;

    public GameServiceImpl(DiceService diceService) {
        this.diceService = diceService;
        this.bufferedReader = getReader();
    }

    public void play() throws IOException {
        startGame();
    }

    public Game initialize() throws IOException {
        System.out.println("Enter number of players: ");
        String input;
        do {
            input = bufferedReader.readLine();
        } while (!isValidNumber(input, 10));
        int numberOfPlayers = Integer.parseInt(input);
        System.out.println("Enter number of rounds: ");
        do {
            input = bufferedReader.readLine();
        } while (!isValidNumber(input, 100));
        int numberOfRounds = Integer.parseInt(input);
        System.out.println("Enter number of dice: ");
        do {
            input = bufferedReader.readLine();
        } while (!isValidNumber(input, 100));
        int numberOfDice = Integer.parseInt(input);
        List<Player> players = new ArrayList<>(numberOfPlayers);
        System.out.println(String.format("Enter %s names: ", numberOfPlayers));
        for (int i = 0; i < numberOfPlayers; i++) {
            String name;
            do {
                name = bufferedReader.readLine();
            } while (!isUniqueName(name, players));
            Player player = new Player(name);
            players.add(player);
        }
        return new Game(numberOfRounds, numberOfDice, players);
    }

    private void startGame() throws IOException {
        System.out.println("Game started");
        rollingDiceIO();
    }

    private void rollingDiceIO() throws IOException {
        Game game = initialize();
        for (int i = 0; i < game.getRounds(); i++) {
            for (Player player : game.getPlayers()) {
                addResultToPlayer(player, game);
            }
        }
        Collections.sort(game.getPlayers());
        System.out.println("Best 3 players: ");
        int size = game.getPlayers().size() <= 3 ? game.getPlayers().size() : 3;
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("Player No %d [%s] scored: %d"
                    , i + 1 , game.getPlayers().get(i).getName(), game.getPlayers().get(i).getResult()));
        }
    }

    private void addResultToPlayer(Player player, Game game) {
        int previousResult = player.getResult();
        int currentResult = getResultSum(diceService.roll(game.getNumberOfDice()));
        player.setResult(previousResult + currentResult);
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

    private boolean isValidNumber(String input, int maxValue) {
        try {
            int number = Integer.parseInt(input);
            if (number <= 0 || number > maxValue) {
                System.out.println("Please enter valid number");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number");
            return false;
        }
        return true;
    }

    private boolean isUniqueName(String name, List<Player> players) {
        if (players.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()).size() > 0) {
            System.out.println("This name already exists, please enter different name");
            return false;
        }
        return true;
    }
}
