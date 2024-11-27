package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewController;
import interface_adapter.menu.MenuViewPresenter;
import interface_adapter.player.PlayerViewModel;
import interface_adapter.team_compare.TeamCompareController;
import interface_adapter.team_compare.TeamComparePresenter;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;
import use_case.menu.MenuViewInteractor;
import use_case.team_compare.TeamCompareInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

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
    private MenuView menuView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addPlayerView() {
        playerViewModel = new PlayerViewModel();
        playerView = new PlayerView(playerViewModel);
        cardPanel.add(playerView, playerView.getViewName());
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
                teamCompareSuccessViewModel, viewManagerModel);
        final TeamCompareInteractor teamCompareInteractor = new TeamCompareInteractor(teamComparePresenter);
        final TeamCompareController teamCompareController = new TeamCompareController(teamCompareInteractor);
        teamCompareView.setTeamCompareController(teamCompareController);
        return this;
    }

    public AppBuilder addMenuView() {
        menuView = new MenuView();
        cardPanel.add(menuView, menuView.getViewName());
        return this;
    }

    public AppBuilder addMenuUseCase() {
        final MenuViewPresenter menuViewPresenter = new MenuViewPresenter(teamCompareViewModel, viewManagerModel);
        final MenuViewInteractor menuViewInteractor = new MenuViewInteractor(menuViewPresenter);
        final MenuViewController menuViewController = new MenuViewController(menuViewInteractor);
        menuView.setMenuViewController(menuViewController);
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
