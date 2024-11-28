package interface_adapter.team_compare;

import use_case.team_compare.TeamCompareInputData;
import use_case.team_compare.TeamCompareInteractor;

public class TeamCompareController {

    private final TeamCompareInteractor teamCompareInteractor;

    public TeamCompareController(TeamCompareInteractor teamCompareInteractor) {
        this.teamCompareInteractor = teamCompareInteractor;
    }

    public void execute(String team1, String team2) {
        final TeamCompareInputData teamCompareInputData = new TeamCompareInputData(team1, team2);
        teamCompareInteractor.execute(teamCompareInputData);
    }

    public void switchToMenuView() {
        teamCompareInteractor.switchToMenuView();
    }
}
