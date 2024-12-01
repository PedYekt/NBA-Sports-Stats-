package use_case.player;

import java.util.List;

import entity.PlayerData;

/**
 * ViewPlayersResponseModel class to get player data.
 */
public class ViewPlayersResponseModel {
    private final List<PlayerData> players;

    public ViewPlayersResponseModel(List<PlayerData> players) {
        this.players = players;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }
}
