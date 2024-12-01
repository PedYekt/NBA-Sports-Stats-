package interface_adapter.player;

import java.util.List;

import entity.PlayerData;

/**
 * The state of the player view.
 */
public class PlayerState {

    private List<PlayerData> players;
    private String error;

    public List<PlayerData> getPlayers() {
        return players;
    }

    public String getError() {
        return error;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

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
