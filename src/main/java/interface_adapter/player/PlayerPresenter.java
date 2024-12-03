package interface_adapter.player;

import interface_adapter.ViewManagerModel;
import use_case.player.ViewPlayersOutputBoundary;
import use_case.player.ViewPlayersResponseModel;
import view.MenuView;

/**
 * The player presenter.
 */
public class PlayerPresenter implements ViewPlayersOutputBoundary {
    private final PlayerViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuView menuView;

    /**
     * Constructor for PlayerPresenter.
     * @param viewModel the view model
     * @param viewManagerModel the view manager model
     * @param menuView the menu view
     */
    public PlayerPresenter(PlayerViewModel viewModel, ViewManagerModel viewManagerModel, MenuView menuView) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuView = menuView;
    }

    @Override
    public void presentPlayers(ViewPlayersResponseModel responseModel) {
        final PlayerState state = new PlayerState();
        state.setPlayers(responseModel.getPlayers());
        viewModel.setState(state);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
