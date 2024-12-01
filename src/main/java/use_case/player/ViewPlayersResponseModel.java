package use_case.player;

import entity.PlayerData;
import java.util.List;

public class ViewPlayersResponseModel {
    private final List<PlayerData> players;

    public ViewPlayersResponseModel(List<PlayerData> players) {
        this.players = players;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }
}
