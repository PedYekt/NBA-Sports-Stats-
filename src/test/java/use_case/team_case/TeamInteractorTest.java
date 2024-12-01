package use_case.team_case;

import entity.TeamData;
import interface_adapter.ViewManagerModel;
import interface_adapter.team.ViewViewTeamPresenter;
import interface_adapter.team.ViewTeamViewModel;
import org.junit.jupiter.api.Test;
import use_case.view_team.ViewTeamInteractor;
import use_case.view_team.ViewTeamResponseModel;
import view.MenuView;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamInteractorTest {

    @Test
    void successTest() {
        // Arrange
        List<TeamData> teams = Arrays.asList(
                new TeamData("Team A", "East", "Atlantic", "New York", 100, 10, 5),
                new TeamData("Team B", "West", "Pacific", "Los Angeles", 95, 8, 7)
        );

        ViewTeamViewModel viewModel = new ViewTeamViewModel();
        ViewManagerModel manager = new ViewManagerModel();
        MenuView menu = new MenuView();

        ViewViewTeamPresenter presenter = new ViewViewTeamPresenter(menu, manager, viewModel) {
            @Override
            public void presentTeams(ViewTeamResponseModel requestModel) {
                List<TeamData> receivedTeams = requestModel.getTeams();
                assertEquals(2, receivedTeams.size());

                // Verify details of the first team
                assertEquals("Team A", receivedTeams.get(0).getTeamName());
                assertEquals("East", receivedTeams.get(0).getConference());
                assertEquals("Atlantic", receivedTeams.get(0).getDivision());
                assertEquals("New York", receivedTeams.get(0).getCity());
                assertEquals(100, receivedTeams.get(0).getPoints());
                assertEquals(10, receivedTeams.get(0).getTurnovers());
                assertEquals(5, receivedTeams.get(0).getSteals());

                // Verify details of the second team
                assertEquals("Team B", receivedTeams.get(1).getTeamName());
                assertEquals("West", receivedTeams.get(1).getConference());
                assertEquals("Pacific", receivedTeams.get(1).getDivision());
                assertEquals("Los Angeles", receivedTeams.get(1).getCity());
                assertEquals(95, receivedTeams.get(1).getPoints());
                assertEquals(8, receivedTeams.get(1).getTurnovers());
                assertEquals(7, receivedTeams.get(1).getSteals());
            }

            @Override
            public void switchToMenuView() {
                // No specific assertion for this method in this test
            }
        };

        ViewTeamInteractor interactor = new ViewTeamInteractor(presenter) {
            @Override
            public void loadTeams() {
                // Simulate data loading in the overridden method
                ViewTeamResponseModel requestModel = new ViewTeamResponseModel(teams);
                presenter.presentTeams(requestModel);
            }
        };

        // Act
        interactor.loadTeams();
        interactor.switchToMenuView(); // Ensures no exception is thrown
    }
}



