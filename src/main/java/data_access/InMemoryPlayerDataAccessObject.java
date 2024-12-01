package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.PlayerData;
import entity.PlayerRepository;
import use_case.player.FetchPlayerDataUseCase;

/**
 * In-memory implementation of the PlayerRepository interface.
 */
public class InMemoryPlayerDataAccessObject implements PlayerRepository {
    private final List<PlayerData> playerDataList;

    /**
     * Constructor to initialize the in-memory data store.
     */
    public InMemoryPlayerDataAccessObject() {
        this.playerDataList = new ArrayList<>();
        populateData();
    }

    /**
     * Populates the in-memory data store with data fetched from the use case.
     */
    private void populateData() {
        playerDataList.addAll(FetchPlayerDataUseCase.fetchAndReturnPlayerData());
    }

    /**
     * Retrieves all players from the in-memory data store.
     *
     * @return a list of all player data
     */
    @Override
    public List<PlayerData> getAllPlayers() {
        return new ArrayList<>(playerDataList);
    }

    /**
     * Retrieves a player by name from the in-memory data store.
     *
     * @param playerName the name of the player to retrieve
     * @return the player data object
     */
    @Override
    public PlayerData getPlayerByName(String playerName) {
        for (PlayerData playerData : playerDataList) {
            if (playerData.getPlayerName().equalsIgnoreCase(playerName)) {
                return playerData;
            }
        }
        return null;
    }
}
