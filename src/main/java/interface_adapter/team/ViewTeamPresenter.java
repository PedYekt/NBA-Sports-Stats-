package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import use_case.view_team.ViewTeamOutputBoundary;
import use_case.view_team.ViewTeamResponseModel;
import view.MenuView;

/**
 * The presenter of the ViewTeam use case.
 */
public class ViewTeamPresenter implements ViewTeamOutputBoundary {

    private final MenuView menuView;
    private final ViewManagerModel viewManagerModel;
    private final ViewTeamViewModel viewModel;

    public ViewTeamPresenter(MenuView menuView, ViewManagerModel viewManagerModel, ViewTeamViewModel viewModel) {
        this.menuView = menuView;
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }

    /**
     * Present the team data.
     * @param requestModel the team request model to use for.
     */
    public void presentTeams(ViewTeamResponseModel requestModel) {
        final TeamState state = new TeamState();
        state.setTeams(requestModel.getTeams());
        viewModel.setState(state);
    }

    /**
     * Switches to the menu view.
     */
    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
