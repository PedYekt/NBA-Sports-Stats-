package use_case.player;

/**
 * Output boundary for the ViewPlayers use case.
 */
public interface ViewPlayersOutputBoundary {

    /**
     * Presents the players to the presenter.
     *
     * @param responseModel the response model containing the players
     */
    void presentPlayers(ViewPlayersResponseModel responseModel);

    /**
     * Switches to the menu view.
     */
    void switchToMenuView();
}
