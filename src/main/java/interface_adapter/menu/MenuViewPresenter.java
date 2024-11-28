package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.team.TeamViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;

public class MenuViewPresenter {

    private final TeamCompareViewModel teamCompareViewModel;
    private final TeamViewModel teamViewModel;
    private final ViewManagerModel viewManagerModel;

    public MenuViewPresenter(TeamCompareViewModel teamCompareViewModel, TeamViewModel teamViewModel,
                             ViewManagerModel viewManagerModel) {
        this.teamCompareViewModel = teamCompareViewModel;
        this.teamViewModel = teamViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToTeamCompareView() {
        viewManagerModel.setState(teamCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToTeamView() {
        viewManagerModel.setState(teamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
