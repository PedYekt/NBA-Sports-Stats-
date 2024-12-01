package use_case.player;

/**
 * The Input Boundary for the ViewPlayers use case.
 */
public interface ViewPlayersInputBoundary {
    /**
     * Load players.
     */
    void loadPlayers();

    /**
     * Switch to the menu view.
     */
    void switchToMenuView();
}
