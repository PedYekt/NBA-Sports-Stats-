package use_case.team_compare;

import interface_adapter.ViewManagerModel;
import interface_adapter.team_compare.TeamComparePresenter;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;
import org.junit.jupiter.api.Test;
import view.MenuView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamCompareInteractorTest {

    @Test
    void succesTest() {
        TeamCompareInputData inputData = new TeamCompareInputData("Raptors", "Nuggets");
        TeamCompareViewModel viewModel = new TeamCompareViewModel();
        TeamCompareSuccessViewModel successViewModel = new TeamCompareSuccessViewModel();
        ViewManagerModel manager = new ViewManagerModel();
        MenuView menu = new MenuView();

        TeamComparePresenter presenter = new TeamComparePresenter(viewModel, successViewModel, manager, menu) {
            @Override
            public void prepareSuccessView(TeamCompareOutputData data) {
                assertEquals("Raptors", data.getTeam1());
                assertEquals("Nuggets", data.getTeam2());
            }

            @Override
            public void switchToMenuView() {

            }
        };

        TeamCompareInteractor interactor = new TeamCompareInteractor(presenter);
        interactor.execute(inputData);
        interactor.switchToMenuView();

    }
}
