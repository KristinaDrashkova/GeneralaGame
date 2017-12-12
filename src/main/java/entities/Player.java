package entities;

public class Player implements Comparable<Player> {
    private String name;
    private int result;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public int compareTo(Player player) {
        return player.getResult() - result;
    }
}
