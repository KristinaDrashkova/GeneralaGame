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

    /**
     * @param player from the game
     * @return the player with the higher score
     */
    @Override
    public int compareTo(Player player) {
        return player.getResult() - result;
    }
}
