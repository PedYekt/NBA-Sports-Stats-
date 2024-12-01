package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.player.PlayerViewModel;
import interface_adapter.player_compare.PlayerCompareViewModel;
import interface_adapter.team.ViewTeamViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;

/**
 * The presenter for the MenuView.
 */
public class MenuViewPresenter {

    private final TeamCompareViewModel teamCompareViewModel;
    private final PlayerCompareViewModel playerCompareViewModel;
    private final PlayerViewModel playerViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewTeamViewModel viewTeamViewModel;

    public MenuViewPresenter(TeamCompareViewModel teamCompareViewModel, PlayerCompareViewModel playerCompareViewModel,
                             ViewManagerModel viewManagerModel,
                             PlayerViewModel playerViewModel, ViewTeamViewModel viewTeamViewModel) {
        this.teamCompareViewModel = teamCompareViewModel;
        this.playerCompareViewModel = playerCompareViewModel;
        this.playerViewModel = playerViewModel;
        this.viewTeamViewModel = viewTeamViewModel;
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

    /**
     * Switch to the team view.
     */
    public void switchToTeamView() {
        viewManagerModel.setState(viewTeamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switch to the player compare view.
     */
    public void switchToPlayerCompareView() {
        viewManagerModel.setState(playerCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
