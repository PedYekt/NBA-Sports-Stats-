package entity;

public class PlayerData {
    private String name;
    private int points;

    public PlayerData(String name, int points) {
        this.name = name;
        this.points = points;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
