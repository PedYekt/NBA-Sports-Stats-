package use_case.view_team;

/**
 * Input data for the ViewTeam use case.
 */
public interface ViewTeamInputBoundary {

    /**
     * Loads the team data.
     */
    void loadTeams();

    /**
     * Switches to the menu view.
     */
    void switchToMenuView();
}
