package use_case.player;

/**
 * Input boundary for the ViewPlayers use case.
 */
public interface ViewPlayersInputBoundary {
    /**
     * Loads all players from the data store.
     */
    void loadPlayers();
}
