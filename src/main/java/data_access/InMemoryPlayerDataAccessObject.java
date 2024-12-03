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

    private InMemoryPlayerDataAccessObject(Builder builder) {
        this.playerDataList = builder.playerDataList;
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

    public static class Builder {
        private final List<PlayerData> playerDataList;

        public Builder() {
            this.playerDataList = new ArrayList<>();
        }

        /**
         * Fetches player data from the use case and adds it to the list.
         *
         * @return the Builder
         */
        public Builder fetchPlayerData() {
            final FetchPlayerDataUseCase fetchPlayerDataUseCase = new FetchPlayerDataUseCase();
            playerDataList.addAll(fetchPlayerDataUseCase.fetchAndReturnPlayerData());
            return this;
        }

        /**
         * Builds the InMemoryPlayerDataAccessObject.
         *
         * @return the InMemoryPlayerDataAccessObject
         */
        public InMemoryPlayerDataAccessObject build() {
            return new InMemoryPlayerDataAccessObject(this);
        }
    }
}