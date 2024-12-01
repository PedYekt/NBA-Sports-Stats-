package interface_adapter.player;

import use_case.player.ViewPlayersInputBoundary;

/**
 * PlayerController class to handle player data.
 */
public class PlayerController {
    /**
     * The interactor.
     */
    private final ViewPlayersInputBoundary interactor;

    public PlayerController(ViewPlayersInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handle load players.
     */
    public void handleLoadPlayers() {
        interactor.loadPlayers();
    }

    /**
     * Switch to the menu view.
     */
    public void switchToMenuView() {
        interactor.switchToMenuView();
    }
}
