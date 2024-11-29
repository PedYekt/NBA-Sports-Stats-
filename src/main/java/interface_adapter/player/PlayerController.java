package interface_adapter.player;

import use_case.player.ViewPlayersInputBoundary;

public class PlayerController {
    private final ViewPlayersInputBoundary interactor;

    public PlayerController(ViewPlayersInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleLoadPlayers() {
        interactor.loadPlayers();
    }

    public void switchToMenuView() {
        interactor.switchToMenuView();
    }
}