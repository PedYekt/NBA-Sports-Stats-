package use_case.player_compare;

import interface_adapter.ViewManagerModel;
import interface_adapter.player_compare.PlayerComparePresenter;
import interface_adapter.player_compare.PlayerCompareSuccessViewModel;
import interface_adapter.player_compare.PlayerCompareViewModel;
import org.junit.jupiter.api.Test;
import view.MenuView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerCompareInteractorTest {

    @Test
    void successTest() {
        PlayerCompareInputData inputData = new PlayerCompareInputData("Giannis Antetokounmpo", "Scottie Barnes");
        PlayerCompareViewModel viewModel = new PlayerCompareViewModel();
        PlayerCompareSuccessViewModel successViewModel = new PlayerCompareSuccessViewModel();
        ViewManagerModel manager = new ViewManagerModel();
        MenuView menu = new MenuView();

        PlayerComparePresenter presenter = new PlayerComparePresenter(viewModel, successViewModel, manager, menu) {
            @Override
            public void prepareSuccessView(PlayerCompareOutputData data) {
                assertEquals("Giannis Antetokounmpo", data.getPlayer1());
                assertEquals("Scottie Barnes", data.getPlayer2());
            }

            @Override
            public void switchToMenuView() {

            }
        };

        PlayerCompareInteractor interactor = new PlayerCompareInteractor(presenter);
        interactor.execute(inputData);
        interactor.switchToMenuView();

    }
}


