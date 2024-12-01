package view;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player_compare.PlayerCompareController;
import interface_adapter.player_compare.PlayerCompareSuccessState;
import interface_adapter.player_compare.PlayerCompareSuccessViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import java.util.List;

/**
 * The Success View for comparing two players.
 */
public class PlayerCompareSuccessView extends JPanel implements
PropertyChangeListener {
    private final String viewName = "player compare success";
    private final String[] columnNames = new String[]{"Stats", "Player One",
            "Player Two"};
    private JScrollPane sp;

    private final PlayerCompareSuccessViewModel playerCompareSuccessViewModel;
    private PlayerCompareController playerCompareController;
    private final InMemoryPlayerDataAccessObject players = new InMemoryPlayerDataAccessObject();
    private final List<PlayerData> playerList;
    private PlayerData correctPlayer1;
    private PlayerData correctPlayer2;

    public PlayerCompareSuccessView(PlayerCompareSuccessViewModel playerCompareSuccessViewModel) {
        this.playerCompareSuccessViewModel = playerCompareSuccessViewModel;
        this.playerCompareSuccessViewModel.addPropertyChangeListener(this);
        this.playerList = players.getAllPlayers();

//        setLayout(new BorderLayout());

        // Create the "Back To Menu" button
        final JButton backButton = new JButton("Back to Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playerCompareController.switchToMenuView();
            }
        });

        this.add(backButton);
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * React to property change being fired and use state information to build JTable.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PlayerCompareSuccessState state = (PlayerCompareSuccessState) evt.getNewValue();

        for (PlayerData player : playerList) {
            if (Objects.equals(player.getPlayerName(), state.getPlayer1()) && Objects.equals(player.getPlayerName(),
                    state.getPlayer2())) {
                JOptionPane.showMessageDialog(null, "Cannot compare same players");
            }
            else if (Objects.equals(player.getPlayerName(), state.getPlayer1())) {
                correctPlayer1 = new PlayerData(
                        String.valueOf(player.getPlayerName()),
                        String.valueOf(player.getPosition()),
                        player.getSteals(),
                        player.getBlocks(),
                        player.getTurnovers(),
                        player.getPoints(),
                        player.getTeam()
                );
            }
            else if (Objects.equals(player.getPlayerName(), state.getPlayer2())) {
                correctPlayer2 = new PlayerData(
                        String.valueOf(player.getPlayerName()),
                        String.valueOf(player.getPosition()),
                        player.getSteals(),
                        player.getBlocks(),
                        player.getTurnovers(),
                        player.getPoints(),
                        player.getTeam()
                );
            }
        }

        final String[][] data = new String[][] {
                {"Name", correctPlayer1.getPlayerName(), correctPlayer2.getPlayerName()},
                {"Position", correctPlayer1.getPosition(), correctPlayer2.getPosition()},
                {"Steals", String.valueOf(correctPlayer1.getSteals()), String.valueOf(correctPlayer2.getSteals())},
                {"Blocks", String.valueOf(correctPlayer1.getBlocks()), String.valueOf(correctPlayer2.getBlocks())},
                {"Turnovers", String.valueOf(correctPlayer1.getTurnovers()), String.valueOf(correctPlayer2.getTurnovers())},
                {"Points", String.valueOf(correctPlayer1.getPoints()), String.valueOf(correctPlayer2.getPoints())},
                {"Team", correctPlayer1.getTeam(), correctPlayer2.getTeam()},
        };

        final JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        if (sp != null) {
            this.remove(sp);
        }

        sp = new JScrollPane(table);
        this.add(sp);
    }

    public void setPlayerCompareController(PlayerCompareController playerCompareController) {
        this.playerCompareController = playerCompareController;
    }
}
