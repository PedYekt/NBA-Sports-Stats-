package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player_compare.PlayerCompareController;
import interface_adapter.player_compare.PlayerCompareViewModel;

/**
 * The View for when the user is selecting two players to compare.
 */
public class PlayerCompareView extends JPanel {
    private final String viewName = "player compare view";
    private static final int SEARCH_FIELD_COLUMNS = 20;
    private final JTextField searchField1;
    private final JTextField searchField2;

    private final JButton compareButton;
    private final JButton backButton;

    private PlayerCompareViewModel playerCompareViewModel;
    private PlayerCompareController playerCompareController;

    private final JTable playerTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    public PlayerCompareView(PlayerCompareViewModel playerCompareViewModel) {
        this.playerCompareViewModel = playerCompareViewModel;

        final JLabel title = new JLabel(PlayerCompareViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BorderLayout());

        // Create the table model and JTable
        tableModel = new DefaultTableModel(new Object[]{"Player Name", "Position",
                "Steals", "Blocks", "Turnovers", "Points", "Team"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        playerTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        playerTable.setRowSorter(sorter);

        // Create the search field
        searchField1 = new JTextField(SEARCH_FIELD_COLUMNS);
        searchField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                final String text = searchField1.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        searchField2 = new JTextField(SEARCH_FIELD_COLUMNS);
        searchField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                final String text = searchField2.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Create the "Compare" button
        compareButton = new JButton(PlayerCompareViewModel.COMPARE_BUTTON_LABEL);
        compareButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareButton.addActionListener(event -> {
            int[] selectedRows = playerTable.getSelectedRows();

            if (selectedRows.length != 2) {
                JOptionPane.showMessageDialog(null, "Please select exactly two players to compare.");
                return;
            }

            String player1Name = (String) playerTable.getValueAt(selectedRows[0], 0);
            String player2Name = (String) playerTable.getValueAt(selectedRows[1], 0);

            if (player1Name.equals(player2Name)) {
                JOptionPane.showMessageDialog(null, "Please select two different players to compare.");
                return;
            }

            playerCompareController.execute(player1Name, player2Name);
        });

        // Create the "Back" button
        backButton = new JButton("Back To Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(event -> playerCompareController.switchToMenuView());

        // Create a panel for the search field and "Compare" button
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(searchField1);
        this.add(searchField2);
        this.add(compareButton);
        this.add(backButton);

        loadAllPlayers();
    }

    private void loadAllPlayers() {
        tableModel.setRowCount(0);
        InMemoryPlayerDataAccessObject dao = new InMemoryPlayerDataAccessObject();
        List<PlayerData> playerDataList = dao.getAllPlayers();

        for (PlayerData playerData : playerDataList) {
            tableModel.addRow(new Object[]{
                    playerData.getPlayerName(),
                    playerData.getPosition(),
                    String.valueOf(playerData.getSteals()),
                    String.valueOf(playerData.getBlocks()),
                    String.valueOf(playerData.getTurnovers()),
                    String.valueOf(playerData.getPoints()),
                    playerData.getTeam()
            });
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setPlayerCompareController(PlayerCompareController playerCompareController) {
        this.playerCompareController = playerCompareController;
    }
}

