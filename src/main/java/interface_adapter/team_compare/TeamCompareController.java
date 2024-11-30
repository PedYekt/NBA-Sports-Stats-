package interface_adapter.team_compare;

import use_case.team_compare.TeamCompareInputData;
import use_case.team_compare.TeamCompareInteractor;

/**
 * The controller for the Team Compare Use Case.
 */
public class TeamCompareController {

    private final TeamCompareInteractor teamCompareInteractor;

    public TeamCompareController(TeamCompareInteractor teamCompareInteractor) {
        this.teamCompareInteractor = teamCompareInteractor;
    }

    /**
     * Creates input data and calls the team compare interactor.
     * @param team1 the first team selected by the user
     * @param team2 the second team selected by the user
     */
    public void execute(String team1, String team2) {
        final TeamCompareInputData teamCompareInputData = new TeamCompareInputData(team1, team2);
        teamCompareInteractor.execute(teamCompareInputData);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        teamCompareInteractor.switchToMenuView();
    }
}
