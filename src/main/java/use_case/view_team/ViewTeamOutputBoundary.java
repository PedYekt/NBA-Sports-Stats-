package use_case.view_team;

/**
 * Output boundary for the ViewTeams use case.
 */
public interface ViewTeamOutputBoundary {
    /**
     * Presents the teams to the presenter.
     *
     * @param requestModel the request model containing the teams
     */
    void presentTeams(ViewTeamResponseModel requestModel);

    /**
     * Switches to the menu view.
     */
    void switchToMenuView();
}
