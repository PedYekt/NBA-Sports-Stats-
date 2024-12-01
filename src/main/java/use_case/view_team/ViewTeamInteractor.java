package use_case.view_team;

import java.util.List;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team.ViewViewTeamPresenter;

/**
 * The interactor of ViewTeam use case.
 */
public class ViewTeamInteractor implements ViewTeamInputBoundary {

    private final ViewViewTeamPresenter viewTeamPresenter;

    public ViewTeamInteractor(ViewViewTeamPresenter viewTeamPresenter) {
        this.viewTeamPresenter = viewTeamPresenter;
    }

    /**
     * Loads the team data.
     */
    public void loadTeams() {
        final List<TeamData> teams = new InMemoryTeamDataAccessObject().getAllTeams();
        final ViewTeamResponseModel requestModel = new ViewTeamResponseModel(teams);
        viewTeamPresenter.presentTeams(requestModel);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        viewTeamPresenter.switchToMenuView();
    }
}
