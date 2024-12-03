package use_case.view_team;

import java.util.List;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team.ViewTeamPresenter;

/**
 * The interactor of ViewTeam use case.
 */
public class ViewTeamInteractor implements ViewTeamInputBoundary {

    private final ViewTeamPresenter viewTeamPresenter;

    public ViewTeamInteractor(ViewTeamPresenter viewTeamPresenter) {
        this.viewTeamPresenter = viewTeamPresenter;
    }

    /**
     * Loads the team data and calls the ViewTeam presenter.
     */
    public void loadTeams() {
        final List<TeamData> teams = new
                InMemoryTeamDataAccessObject
                        .Builder()
                .fetchTeamData()
                .build()
                .getAllTeams();
        final ViewTeamResponseModel responseModel = new ViewTeamResponseModel(teams);
        viewTeamPresenter.presentTeams(responseModel);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        viewTeamPresenter.switchToMenuView();
    }
}
