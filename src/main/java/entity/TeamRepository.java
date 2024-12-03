package entity;

import java.util.List;

/**
 * Interface for a repository of Team data.
 */
public interface TeamRepository {
    /**
     * Retrieves all players from the data store.
     *
     * @return a list of all player data
     */
    public List<TeamData> getAllTeams();

    /**
     * Retrieves a player by name from the data store.
     *
     * @param teamName the name of the player to retrieve
     * @return the player data object
     */
    public TeamData getTeamByName(String teamName);
}
