package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int rounds;
    private int numberOfDice;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
