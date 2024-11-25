package use_case.team_compare;

import interface_adapter.team_compare.TeamComparePresenter;

public class TeamCompareInteractor {

    private final TeamComparePresenter teamComparePresenter;

    public TeamCompareInteractor(TeamComparePresenter teamComparePresenter) {
        this.teamComparePresenter = teamComparePresenter;
    }

    public void switchToTeamCompareSuccessView() {
        teamComparePresenter.switchToTeamCompareSuccessView();
    }

    public void execute(TeamCompareInputData teamCompareInputData) {
        final TeamCompareOutputData teamCompareOutputData = new TeamCompareOutputData(false,
                teamCompareInputData.getTeam1(), teamCompareInputData.getTeam2());
        teamComparePresenter.prepareSuccessView(teamCompareOutputData);
    }
}
