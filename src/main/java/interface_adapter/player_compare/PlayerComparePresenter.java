package interface_adapter.player_compare;

import interface_adapter.ViewManagerModel;
import use_case.player_compare.PlayerCompareOutputData;
import view.MenuView;

/**
 * Presenter for the player compare use case.
 */

public class PlayerComparePresenter {

    private final PlayerCompareViewModel playerCompareViewModel;
    private final PlayerCompareSuccessViewModel playerCompareSuccessViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuView menuView;

    public PlayerComparePresenter(PlayerCompareViewModel playerCompareViewModel,
                                  PlayerCompareSuccessViewModel playerCompareSuccessViewModel,
                                  ViewManagerModel viewManagerModel, MenuView menuView) {
        this.playerCompareViewModel = playerCompareViewModel;
        this.playerCompareSuccessViewModel = playerCompareSuccessViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuView = menuView;
    }

    public void prepareSuccessView(PlayerCompareOutputData response) {

        final PlayerCompareSuccessState successState = playerCompareSuccessViewModel.getState();
        successState.setPlayer1(response.getPlayer1());
        successState.setPlayer2(response.getPlayer2());
        this.playerCompareSuccessViewModel.setState(successState);
        playerCompareSuccessViewModel.firePropertyChanged();

        viewManagerModel.setState(playerCompareSuccessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

