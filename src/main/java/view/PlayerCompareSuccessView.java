package view;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player_compare.PlayerCompareController;
import interface_adapter.player_compare.PlayerCompareSuccessState;
import interface_adapter.player_compare.PlayerCompareSuccessViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;

/**
 * The Success View for comparing two players.
 */
public class PlayerCompareSuccessView extends JPanel implements PropertyChangeListener {

    private final String viewName = "player compare success";
    private final String[] columnNames = {"Stats", "Player One", "Player Two"};
    private JScrollPane sp;

    private final PlayerCompareSuccessViewModel playerCompareSuccessViewModel;
    private PlayerCompareController playerCompareController;
    private final List<PlayerData> playerList;

    private PlayerData correctPlayer1;
    private PlayerData correctPlayer2;

    public PlayerCompareSuccessView(PlayerCompareSuccessViewModel playerCompareSuccessViewModel) {
        this.playerCompareSuccessViewModel = playerCompareSuccessViewModel;
        this.playerCompareSuccessViewModel.addPropertyChangeListener(this);

        // Load all players
        InMemoryPlayerDataAccessObject players = new InMemoryPlayerDataAccessObject();
        this.playerList = players.getAllPlayers();

        // Set layout
        setLayout(new BorderLayout());

        // Create the "Back to Menu" button
        JButton backButton = new JButton("Back to Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(evt -> {
            if (playerCompareController != null) {
                playerCompareController.switchToMenuView();
            } else {
                JOptionPane.showMessageDialog(null, "Controller is not initialized.");
            }
        });
        this.add(backButton, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * React to property change being fired and use state information to build JTable.
     *
     * @param evt the PropertyChangeEvent to react to
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PlayerCompareSuccessState state = (PlayerCompareSuccessState) evt.getNewValue();

        // Check if the same player is selected
        if (Objects.equals(state.getPlayer1(), state.getPlayer2())) {
            JOptionPane.showMessageDialog(null, "Cannot compare the same players.");
            return;
        }

        // Find the correct players
        correctPlayer1 = null;
        correctPlayer2 = null;
        for (PlayerData player : playerList) {
            if (Objects.equals(player.getPlayerName(), state.getPlayer1())) {
                correctPlayer1 = player;
            } else if (Objects.equals(player.getPlayerName(), state.getPlayer2())) {
                correctPlayer2 = player;
            }
        }

        if (correctPlayer1 == null || correctPlayer2 == null) {
            JOptionPane.showMessageDialog(null, "One or both players could not be found.");
            return;
        }

        // Create table data
        String[][] data = {
                {"Name", correctPlayer1.getPlayerName(), correctPlayer2.getPlayerName()},
                {"Position", correctPlayer1.getPosition(), correctPlayer2.getPosition()},
                {"Team", correctPlayer1.getTeam(), correctPlayer2.getTeam()},
                {"Points", String.valueOf(correctPlayer1.getPoints()), String.valueOf(correctPlayer2.getPoints())},
                {"Turnovers", String.valueOf(correctPlayer1.getTurnovers()), String.valueOf(correctPlayer2.getTurnovers())},
                {"Steals", String.valueOf(correctPlayer1.getSteals()), String.valueOf(correctPlayer2.getSteals())},
                {"Blocks", String.valueOf(correctPlayer1.getBlocks()), String.valueOf(correctPlayer2.getBlocks())},
        };

        // Create and display the table
        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        if (sp != null) {
            this.remove(sp);
        }

        sp = new JScrollPane(table);
        this.add(sp, BorderLayout.CENTER);

        // Refresh the view
        this.revalidate();
        this.repaint();
    }

    public void setPlayerCompareController(PlayerCompareController playerCompareController) {
        this.playerCompareController = playerCompareController;
    }
}