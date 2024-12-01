package interface_adapter.player_compare;

import interface_adapter.ViewModel;

/**
 * The view model for the PlayerCompareSuccessView.
 */

public class PlayerCompareSuccessViewModel extends ViewModel<PlayerCompareSuccessState> {

    public static final String TITLE_LABEL = "PLAYER COMPARISON";
    public static final String PLAYER1_LABEL = "Player 1";
    public static final String PLAYER2_LABEL = "Player 2";
    public static final String COMPARE_BUTTON_LABEL = "Compare";

    public PlayerCompareSuccessViewModel() {
        super("player compare success");
        setState(new PlayerCompareSuccessState());
    }
}
