package entities;

import java.util.List;

public class Game {
    private int rounds;
    private int numberOfDice;
    private List<Player> players;

    public Game(int rounds, int numberOfDice, List<Player> players) {
        this.rounds = rounds;
        this.numberOfDice = numberOfDice;
        this.players = players;
    }

    public int getRounds() {
        return rounds;
    }

    private void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    private void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void setPlayers(List<Player> players) {
        this.players = players;
    }
}
