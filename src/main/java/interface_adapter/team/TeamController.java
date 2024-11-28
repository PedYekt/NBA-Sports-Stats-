package interface_adapter.team;

import use_case.team.TeamInteractor;

public class TeamController {

    private final TeamInteractor teamInteractor;

    public TeamController(TeamInteractor interactor) {
        this.teamInteractor = interactor;
    }

    public void switchToMenuView() {
        teamInteractor.switchToMenuView();
    }
}
