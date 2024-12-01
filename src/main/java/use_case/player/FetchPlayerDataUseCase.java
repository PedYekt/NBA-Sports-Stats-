package use_case.player;

import java.util.Collections;
import java.util.List;

import api.PlayerApi;
import entity.PlayerData;
import interface_adapter.player.PlayerDataParser;

/**
 * FetchPlayerDataUseCase class to fetch player data.
 */
public class FetchPlayerDataUseCase {
    public FetchPlayerDataUseCase() {
        // Default constructor
    }

    /**
     * Fetch and return player data.
     * @return list of player data
     */
    public static List<PlayerData> fetchAndReturnPlayerData() {
        List<PlayerData> playerDataList = Collections.emptyList();
        final String jsonString = PlayerApi.fetchAllPlayersData();
        if (jsonString != null) {
            final PlayerDataParser parser = new PlayerDataParser();
            playerDataList = parser.fetchAndParseAllPlayers(jsonString);
        }
        return playerDataList;
    }
}
