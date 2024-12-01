package interface_adapter.team;

import use_case.view_team.ViewTeamInteractor;

/**
 * The controller for the ViewTeam use case.
 */
public class ViewTeamController {

    private final ViewTeamInteractor teamInteractor;

    public ViewTeamController(ViewTeamInteractor interactor) {
        this.teamInteractor = interactor;
    }

    /**
     * Handle to load the team data.
     */
    public void handleLoadTeams() {
        teamInteractor.loadTeams();
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        teamInteractor.switchToMenuView();
    }
}
