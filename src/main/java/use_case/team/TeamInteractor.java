package use_case.team;

import interface_adapter.team.TeamPresenter;

public class TeamInteractor {

    private final TeamPresenter teamPresenter;

    public TeamInteractor(TeamPresenter teamPresenter) {
        this.teamPresenter = teamPresenter;
    }

    public void switchToMenuView() {
        teamPresenter.switchToMenuView();
    }
}
