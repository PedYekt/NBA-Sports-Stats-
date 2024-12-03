package interface_adapter.player;

import java.util.List;

import entity.PlayerData;

/**
 * The state of the player view.
 */
public class PlayerState {

    private List<PlayerData> players;
    private String error;

    /**
     * Constructor for PlayerState.
     */
    public List<PlayerData> getPlayers() {
        return players;
    }

    /**
     * Get error message.
     * @return error message
     */
    public String getError() {
        return error;
    }

    /**
     * Set players.
     * @param players list of players
     */
    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

    /**
     * Set error message.
     * @param error error message
     */
    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "PlayerState{"
                + "players=" + players
                + ", error='" + error + '\''
                + '}';
    }
}
