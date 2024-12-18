package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryPlayerDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewController;
import interface_adapter.menu.MenuViewPresenter;
import interface_adapter.player.PlayerController;
import interface_adapter.player.PlayerPresenter;
import interface_adapter.player.PlayerViewModel;
import interface_adapter.team.ViewTeamController;
import interface_adapter.team.ViewTeamPresenter;
import interface_adapter.team.ViewTeamViewModel;
import interface_adapter.team_compare.TeamCompareController;
import interface_adapter.team_compare.TeamComparePresenter;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;
import interface_adapter.player_compare.PlayerCompareController;
import interface_adapter.player_compare.PlayerComparePresenter;
import interface_adapter.player_compare.PlayerCompareSuccessViewModel;
import interface_adapter.player_compare.PlayerCompareViewModel;
import use_case.menu.MenuViewInteractor;
import use_case.player.ViewPlayersInteractor;
import use_case.view_team.ViewTeamInteractor;
import use_case.team_compare.TeamCompareInteractor;
import use_case.player_compare.PlayerCompareInteractor;
import view.MenuView;
import view.PlayerView;
import view.TeamCompareSuccessView;
import view.TeamCompareView;
import view.PlayerCompareSuccessView;
import view.PlayerCompareView;
import view.TeamView;
import view.ViewManager;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private PlayerView playerView;
    private PlayerViewModel playerViewModel;
    private TeamCompareView teamCompareView;
    private TeamCompareViewModel teamCompareViewModel;
    private TeamCompareSuccessView teamCompareSuccessView;
    private TeamCompareSuccessViewModel teamCompareSuccessViewModel;
    private PlayerCompareView playerCompareView;
    private PlayerCompareViewModel playerCompareViewModel;
    private PlayerCompareSuccessView playerCompareSuccessView;
    private PlayerCompareSuccessViewModel playerCompareSuccessViewModel;
    private ViewTeamViewModel viewTeamViewModel;
    private TeamView teamView;
    private MenuView menuView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addPlayerView() {
        playerViewModel = new PlayerViewModel();
        playerView = new PlayerView(new PlayerController(new ViewPlayersInteractor(new
                InMemoryPlayerDataAccessObject.Builder().fetchPlayerData().build(),
                new PlayerPresenter(playerViewModel, viewManagerModel, menuView))), playerViewModel);
        cardPanel.add(playerView, playerView.getViewName());
        return this;
    }

    public AppBuilder addPlayerUseCase() {
        final PlayerPresenter playerPresenter = new PlayerPresenter(playerViewModel, viewManagerModel, menuView);
        final ViewPlayersInteractor playerInteractor = new ViewPlayersInteractor(new
                InMemoryPlayerDataAccessObject.Builder().fetchPlayerData().build(), playerPresenter);
        final PlayerController playerController = new PlayerController(playerInteractor);
        playerView.setPlayerController(playerController);
        return this;
    }

    public AppBuilder addTeamCompareView() {
        teamCompareViewModel = new TeamCompareViewModel();
        teamCompareView = new TeamCompareView(teamCompareViewModel);
        cardPanel.add(teamCompareView, teamCompareView.getViewName());
        return this;
    }

    public AppBuilder addTeamCompareSuccessView() {
        teamCompareSuccessViewModel = new TeamCompareSuccessViewModel();
        teamCompareSuccessView = new TeamCompareSuccessView(teamCompareSuccessViewModel);
        cardPanel.add(teamCompareSuccessView, teamCompareSuccessView.getViewName());
        return this;
    }

    public AppBuilder addTeamCompareUseCase() {
        final TeamComparePresenter teamComparePresenter = new TeamComparePresenter(teamCompareViewModel,
                teamCompareSuccessViewModel, viewManagerModel, menuView);
        final TeamCompareInteractor teamCompareInteractor = new TeamCompareInteractor(teamComparePresenter);
        final TeamCompareController teamCompareController = new TeamCompareController(teamCompareInteractor);
        teamCompareView.setTeamCompareController(teamCompareController);
        teamCompareSuccessView.setTeamCompareController(teamCompareController);
        return this;
    }

    public AppBuilder addPlayerCompareView() {
        playerCompareViewModel = new PlayerCompareViewModel();
        playerCompareView = new PlayerCompareView(playerCompareViewModel);
        cardPanel.add(playerCompareView, playerCompareView.getViewName());
        return this;
    }

    public AppBuilder addPlayerCompareSuccessView() {
        playerCompareSuccessViewModel = new PlayerCompareSuccessViewModel();
        playerCompareSuccessView = new PlayerCompareSuccessView(playerCompareSuccessViewModel);
        cardPanel.add(playerCompareSuccessView, playerCompareSuccessView.getViewName());
        return this;
    }

    public AppBuilder addPlayerCompareUseCase() {
        final PlayerComparePresenter playerComparePresenter = new PlayerComparePresenter(playerCompareViewModel,
                playerCompareSuccessViewModel, viewManagerModel, menuView);
        final PlayerCompareInteractor playerCompareInteractor = new PlayerCompareInteractor(playerComparePresenter);
        final PlayerCompareController playerCompareController = new PlayerCompareController(playerCompareInteractor);
        playerCompareView.setPlayerCompareController(playerCompareController);
        playerCompareSuccessView.setPlayerCompareController(playerCompareController);
        return this;
    }

    public AppBuilder addMenuView() {
        menuView = new MenuView();
        cardPanel.add(menuView, menuView.getViewName());
        return this;
    }

    public AppBuilder addMenuUseCase() {
        final MenuViewPresenter menuViewPresenter = new MenuViewPresenter(teamCompareViewModel, playerCompareViewModel,
                viewManagerModel, playerViewModel, viewTeamViewModel);
        final MenuViewInteractor menuViewInteractor = new MenuViewInteractor(menuViewPresenter);
        final MenuViewController menuViewController = new MenuViewController(menuViewInteractor);
        menuView.setMenuViewController(menuViewController);
        return this;

    }

    public AppBuilder addTeamView() {
        viewTeamViewModel = new ViewTeamViewModel();
        teamView = new TeamView(viewTeamViewModel);
        final ViewTeamPresenter viewTeamPresenter = new ViewTeamPresenter(menuView, viewManagerModel, viewTeamViewModel);
        final ViewTeamInteractor teamInteractor = new ViewTeamInteractor(viewTeamPresenter);
        ViewTeamController viewTeamController = new ViewTeamController(teamInteractor);
        teamView.setTeamController(viewTeamController);
        cardPanel.add(teamView, teamView.getViewName());
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("NBA Stat Tracker");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
