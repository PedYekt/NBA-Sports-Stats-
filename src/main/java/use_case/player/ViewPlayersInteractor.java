package use_case.player;

import java.util.List;

import entity.PlayerData;
import entity.PlayerRepository;

/**
 * Interactor for the ViewPlayers use case.
 */
public class ViewPlayersInteractor implements ViewPlayersInputBoundary {
    private final PlayerRepository playerRepository;
    private final ViewPlayersOutputBoundary presenter;

    public ViewPlayersInteractor(PlayerRepository playerRepository, ViewPlayersOutputBoundary presenter) {
        this.playerRepository = playerRepository;
        this.presenter = presenter;
    }

    @Override
    public void loadPlayers() {
        final List<PlayerData> players = playerRepository.getAllPlayers();
        final ViewPlayersResponseModel responseModel = new ViewPlayersResponseModel(players);
        presenter.presentPlayers(responseModel);
    }

    @Override
    public void switchToMenuView() {
        presenter.switchToMenuView();
    }

    /**
     * Get all players.
     * @return list of players
     */
    public List<PlayerData> getPlayers() {
        return playerRepository.getAllPlayers();
    }
}
