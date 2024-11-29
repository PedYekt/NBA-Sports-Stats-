package interface_adapter.player;

import use_case.player.ViewPlayersOutputBoundary;
import use_case.player.ViewPlayersResponseModel;

/**
 * The player presenter.
 */
public class PlayerPresenter implements ViewPlayersOutputBoundary {
    private final PlayerViewModel viewModel;

    public PlayerPresenter(PlayerViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentPlayers(ViewPlayersResponseModel responseModel) {
        final PlayerState state = new PlayerState();
        state.setPlayers(responseModel.getPlayers());
        viewModel.setState(state);
    }
}
