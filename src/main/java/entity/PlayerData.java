package entity;

/**
 * A class representing a player's data.
 */
public class PlayerData {
    private final String playerName;
    private final String position;
    private final int steals;
    private final int blocks;
    private final int turnovers;
    private final int points;
    private final String team;

    /**
     * Create a new PlayerData object.
     * @param playerName the player's name
     * @param position the player's position
     * @param steals the player's steals
     * @param blocks the player's blocks
     * @param turnovers the player's turnovers
     * @param points the player's points
     * @param team the player's team
     */
    public PlayerData(String playerName, String position,
                      int steals, int blocks, int turnovers,
                      int points, String team) {
        this.playerName = playerName;
        this.position = position;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.points = points;
        this.team = team;
    }

    /**
     * Get the player's name.
     * @return the player's name
     */
    @Override
    public String toString() {
        return "PlayerData{"
                + "playerName='" + playerName + '\''
                + ", position='" + position + '\''
                + ", steals=" + steals + '\''
                + ", blocks=" + blocks + '\''
                + ", turnovers=" + turnovers + '\''
                + ", points=" + points + '\''
                + ", team='" + team + '\''
                + '}';
    }

    /**
     * Get the player's name.
     * @return the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Get the player's position.
     * @return the player's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Get the player's steals.
     * @return the player's steals
     */
    public int getSteals() {
        return steals;
    }

    /**
     * Get the player's blocks.
     * @return the player's blocks
     */
    public int getBlocks() {
        return blocks;
    }

    /**
     * Get the player's turnovers.
     * @return the player's turnovers
     */
    public int getTurnovers() {
        return turnovers;
    }

    /**
     * Get the player's points.
     * @return the player's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Get the player's team.
     * @return the player's team
     */
    public String getTeam() {
        return team;
    }
}
