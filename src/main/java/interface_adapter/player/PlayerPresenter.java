package interface_adapter.player;

import interface_adapter.ViewManagerModel;
import use_case.player.ViewPlayersOutputBoundary;
import use_case.player.ViewPlayersResponseModel;
import view.MenuView;
import view.PlayerView;

/**
 * The player presenter.
 */
public class PlayerPresenter implements ViewPlayersOutputBoundary {
    private final PlayerViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuView menuView;

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

    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
