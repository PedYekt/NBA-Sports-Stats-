package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.team_compare.TeamCompareViewModel;

public class MenuViewPresenter {

    private final TeamCompareViewModel teamCompareViewModel;
    private final ViewManagerModel viewManagerModel;

    public MenuViewPresenter(TeamCompareViewModel teamCompareViewModel, ViewManagerModel viewManagerModel) {
        this.teamCompareViewModel = teamCompareViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToTeamCompareView() {
        viewManagerModel.setState(teamCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
