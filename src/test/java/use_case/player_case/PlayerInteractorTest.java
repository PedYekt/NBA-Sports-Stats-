package use_case.player_case;

import entity.PlayerData;
import entity.PlayerRepository;
import interface_adapter.ViewManagerModel;
import interface_adapter.player.PlayerPresenter;
import interface_adapter.player.PlayerViewModel;
import org.junit.jupiter.api.Test;
import use_case.player.ViewPlayersInteractor;
import use_case.player.ViewPlayersResponseModel;
import view.MenuView;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerInteractorTest {

    @Test
    void successTest() {
        // Arrange
        List<PlayerData> players = Arrays.asList(
                new PlayerData("John Doe", "Forward", 3, 0, 5, 20, "Team A"),
                new PlayerData("Jane Smith", "Guard", 4, 0, 2, 15, "Team B")
        );

        PlayerViewModel viewModel = new PlayerViewModel();
        ViewManagerModel manager = new ViewManagerModel();
        MenuView menu = new MenuView();

        PlayerPresenter presenter = new PlayerPresenter(viewModel, manager, menu) {
            @Override
            public void presentPlayers(ViewPlayersResponseModel responseModel) {
                List<PlayerData> receivedPlayers = responseModel.getPlayers();
                assertEquals(2, receivedPlayers.size());

                // Verify details of the first player
                assertEquals("John Doe", receivedPlayers.get(0).getPlayerName());
                assertEquals("Forward", receivedPlayers.get(0).getPosition());
                assertEquals("Team A", receivedPlayers.get(0).getTeam());
                assertEquals(20, receivedPlayers.get(0).getPoints());
                assertEquals(5, receivedPlayers.get(0).getTurnovers());
                assertEquals(3, receivedPlayers.get(0).getSteals());

                // Verify details of the second player
                assertEquals("Jane Smith", receivedPlayers.get(1).getPlayerName());
                assertEquals("Guard", receivedPlayers.get(1).getPosition());
                assertEquals("Team B", receivedPlayers.get(1).getTeam());
                assertEquals(15, receivedPlayers.get(1).getPoints());
                assertEquals(2, receivedPlayers.get(1).getTurnovers());
                assertEquals(4, receivedPlayers.get(1).getSteals());
            }

            @Override
            public void switchToMenuView() {
                // No specific assertion for this method in this test
            }
        };

        PlayerRepository playerRepository = new PlayerRepository() {
            @Override
            public List<PlayerData> getAllPlayers() {
                return players;
            }

            @Override
            public PlayerData getPlayerByName(String playerName) {
                return players.stream()
                        .filter(player -> player.getPlayerName().equals(playerName))
                        .findFirst()
                        .orElse(null);
            }
        };

        ViewPlayersInteractor interactor = new ViewPlayersInteractor(playerRepository, presenter) {
            @Override
            public void loadPlayers() {
                // Simulate data loading in the overridden method
                ViewPlayersResponseModel responseModel = new ViewPlayersResponseModel(players);
                presenter.presentPlayers(responseModel);
            }
        };

        // Act
        interactor.loadPlayers();
        interactor.switchToMenuView(); // Ensures no exception is thrown
    }
}