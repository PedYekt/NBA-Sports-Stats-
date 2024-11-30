package use_case.team_compare;

import interface_adapter.team_compare.TeamComparePresenter;

/**
 * The TeamCompare Interactor.
 */
public class TeamCompareInteractor {

    private final TeamComparePresenter teamComparePresenter;

    public TeamCompareInteractor(TeamComparePresenter teamComparePresenter) {
        this.teamComparePresenter = teamComparePresenter;
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        teamComparePresenter.switchToMenuView();
    }

    /**
     * Creates output data and calls the TeamCompare Presenter.
     * @param teamCompareInputData the input data passed in
     */
    public void execute(TeamCompareInputData teamCompareInputData) {
        final TeamCompareOutputData teamCompareOutputData = new TeamCompareOutputData(false,
                teamCompareInputData.getTeam1(), teamCompareInputData.getTeam2());
        teamComparePresenter.prepareSuccessView(teamCompareOutputData);
    }
}
