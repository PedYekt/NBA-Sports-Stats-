package interface_adapter.player;

import use_case.player.ViewPlayersInputBoundary;

/**
 * The player controller.
 */
public class PlayerController {
    private final ViewPlayersInputBoundary interactor;

    /**
     * Creates a new player controller.
     *
     * @param interactor the view players interactor
     */
    public PlayerController(ViewPlayersInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the load players event.
     */
    public void handleLoadPlayers() {
        interactor.loadPlayers();
    }
}
