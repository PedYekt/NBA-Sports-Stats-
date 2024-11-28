package interface_adapter.team_compare;

import interface_adapter.ViewManagerModel;
import use_case.team_compare.TeamCompareOutputData;
import view.MenuView;

public class TeamComparePresenter {

    private final TeamCompareViewModel teamCompareViewModel;
    private final TeamCompareSuccessViewModel teamCompareSuccessViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuView menuView;

    public TeamComparePresenter(TeamCompareViewModel teamCompareViewModel,
                                TeamCompareSuccessViewModel teamCompareSuccessViewModel,
                                ViewManagerModel viewManagerModel, MenuView menuView) {
        this.teamCompareViewModel = teamCompareViewModel;
        this.teamCompareSuccessViewModel = teamCompareSuccessViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuView = menuView;
    }

    public void prepareSuccessView(TeamCompareOutputData response) {

        final TeamCompareSuccessState successState = teamCompareSuccessViewModel.getState();
        successState.setTeam1(response.getTeam1());
        successState.setTeam2(response.getTeam2());
        this.teamCompareSuccessViewModel.setState(successState);
        teamCompareSuccessViewModel.firePropertyChanged();

        viewManagerModel.setState(teamCompareSuccessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
