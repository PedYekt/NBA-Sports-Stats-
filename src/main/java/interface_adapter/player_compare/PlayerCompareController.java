package interface_adapter.player_compare;

import use_case.player_compare.PlayerCompareInputData;
import use_case.player_compare.PlayerCompareInteractor;

/**
 * PlayerCompareController
 */

public class PlayerCompareController {

    private final PlayerCompareInteractor playerCompareInteractor;

    public PlayerCompareController(PlayerCompareInteractor playerCompareInteractor) {
        this.playerCompareInteractor = playerCompareInteractor;
    }

    /**
     * Create input data and call the player compare interactor.
     * @param player1 the first player searched by the user
     * @param player2 the second player searched by the user
     */

    public void execute(String player1, String player2) {
        final PlayerCompareInputData playerCompareInputData = new PlayerCompareInputData(player1, player2);
        playerCompareInteractor.execute(playerCompareInputData);
    }

    /**
     * Switch to the menu view.
     */
    public void switchToMenuView() {
        playerCompareInteractor.switchToMenuView();
    }
}
