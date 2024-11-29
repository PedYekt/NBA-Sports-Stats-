package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.player.PlayerViewModel;
import interface_adapter.team.TeamViewModel;

import interface_adapter.team_compare.TeamCompareViewModel;

public class MenuViewPresenter {

    private final TeamCompareViewModel teamCompareViewModel;
    private final PlayerViewModel playerViewModel;
    private final ViewManagerModel viewManagerModel;
    private final TeamViewModel teamViewModel;

    public MenuViewPresenter(TeamCompareViewModel teamCompareViewModel, ViewManagerModel viewManagerModel,
                             PlayerViewModel playerViewModel, TeamViewModel teamViewModel) {
        this.teamCompareViewModel = teamCompareViewModel;
        this.playerViewModel = playerViewModel;
        this.teamViewModel = teamViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    /**
     * Switch to the team compare view.
     */
    public void switchToTeamCompareView() {
        viewManagerModel.setState(teamCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switch to the player view.
     */
    public void switchToPlayerView() {
        viewManagerModel.setState(playerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    
      public void switchToTeamView() {
        viewManagerModel.setState(teamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
