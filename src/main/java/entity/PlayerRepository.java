package entity;

import java.util.List;

/**
 * Interface for a repository of player data.
 */
public interface PlayerRepository {
    /**
     * Retrieves all players from the data store.
     *
     * @return a list of all player data
     */
    List<PlayerData> getAllPlayers();

    /**
     * Retrieves a player by name from the data store.
     *
     * @param playerName the name of the player to retrieve
     * @return the player data object
     */
    PlayerData getPlayerByName(String playerName);
}
