package entities;

import service.Combination;

import java.util.*;

public class Game {
    private int rounds;
    private int numberOfDice;
    private Map<Player, List<Combination>> players = new LinkedHashMap<>();

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

    public Map<Player, List<Combination>> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Player, List<Combination>> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        List<Combination> combinations = new ArrayList<>(EnumSet.allOf(Combination.class));
        this.players.put(player, combinations);
    }
}
