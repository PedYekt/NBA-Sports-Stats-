package app;

import data_access.InMemoryPlayerDataAccessObject;
import interface_adapter.player.PlayerViewModel;
import view.PlayerView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private PlayerView playerView;
    private PlayerViewModel playerViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addPlayerView() {
        playerViewModel = new PlayerViewModel();
        playerView = new PlayerView(playerViewModel);
        cardPanel.add(playerView, "Player View");
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("NBA Stat Tracker");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        return application;
    }
}
