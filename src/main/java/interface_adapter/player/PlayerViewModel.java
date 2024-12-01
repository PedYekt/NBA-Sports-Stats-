package interface_adapter.player;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the player view.
 */
public class PlayerViewModel extends ViewModel<PlayerState> {

    public static final String TITLE_LABEL = "PLAYER VIEW";
    public static final String SEARCH_LABEL = "Search";
    public static final String VIEW_ALL_BUTTON_LABEL = "View All";

    public PlayerViewModel() {
        super("player view");
        setState(new PlayerState());
    }

}
