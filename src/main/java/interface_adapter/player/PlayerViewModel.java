package interface_adapter.player;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the player view.
 */
public class PlayerViewModel extends ViewModel<PlayerState> {
    /**
     * Constants for the player view.
     */
    public static final String TITLE_LABEL = "PLAYER VIEW";
    /**
     * Constants for the player view.
     */
    public static final String SEARCH_LABEL = "Search";
    /**
     * Constants for the player view.
     */
    public static final String VIEW_ALL_BUTTON_LABEL = "View All";

    /**
     * Constructor for PlayerViewModel.
     */
    public PlayerViewModel() {
        super("player view");
        setState(new PlayerState());
    }

}
