package view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player_compare.PlayerCompareController;
import interface_adapter.player_compare.PlayerCompareViewModel;

/**
 * The View for when the user is selecting two players to compare.
 */
public class PlayerCompareView extends JPanel {

    private final String viewName = "player compare";
    private static final int SEARCH_FIELD_COLUMNS = 20;

    private final JTextField searchField1;
    private final JTextField searchField2;

    private final JButton compareButton;
    private final JButton backButton;

    private final JTable playerTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    private PlayerCompareController playerCompareController;
    private final PlayerCompareViewModel playerCompareViewModel;

    public PlayerCompareView(PlayerCompareViewModel playerCompareViewModel) {
        this.playerCompareViewModel = playerCompareViewModel;

        // Set layout
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel(PlayerCompareViewModel.TITLE_LABEL, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        // Create the table model and JTable
        tableModel = new DefaultTableModel(
                new Object[]{"Player Name", "Position", "Team Name", "Points", "Turnovers", "Steals"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        playerTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        playerTable.setRowSorter(sorter);

        // Create search fields
        searchField1 = new JTextField(SEARCH_FIELD_COLUMNS);
        searchField2 = new JTextField(SEARCH_FIELD_COLUMNS);

        // Add key listeners for search fields
        searchField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                applyFilters();
            }
        });

        searchField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                applyFilters();
            }
        });

        // Create a panel for search fields
        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        searchPanel.add(new JLabel("Search Player 1:"));
        searchPanel.add(searchField1);
        searchPanel.add(new JLabel("Search Player 2:"));
        searchPanel.add(searchField2);

        // Add search panel and table to the center
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(playerTable), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Create the "Compare" button
        compareButton = new JButton(PlayerCompareViewModel.COMPARE_BUTTON_LABEL);
        compareButton.addActionListener(event -> handleCompareAction());

        // Create the "Back to Menu" button
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(event -> {
            if (playerCompareController != null) {
                playerCompareController.switchToMenuView();
            } else {
                JOptionPane.showMessageDialog(null, "Controller is not initialized.");
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(compareButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load all players initially
        loadAllPlayers();
    }

    /**
     * Applies filters from both search fields to the table.
     */
    private void applyFilters() {
        String text1 = searchField1.getText().trim();
        String text2 = searchField2.getText().trim();

        if (text1.isEmpty() && text2.isEmpty()) {
            sorter.setRowFilter(null);
        } else if (!text1.isEmpty() && text2.isEmpty()) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text1));
        } else if (text1.isEmpty() && !text2.isEmpty()) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text2));
        } else {
            sorter.setRowFilter(RowFilter.andFilter(List.of(
                    RowFilter.regexFilter("(?i)" + text1),
                    RowFilter.regexFilter("(?i)" + text2)
            )));
        }
    }

    /**
     * Handles the "Compare" button action.
     */
    private void handleCompareAction() {
        int[] selectedRows = playerTable.getSelectedRows();

        if (selectedRows.length != 2) {
            JOptionPane.showMessageDialog(null, "Please select exactly two players to compare.");
            return;
        }

        // Convert view indices to model indices
        int modelRow1 = playerTable.convertRowIndexToModel(selectedRows[0]);
        int modelRow2 = playerTable.convertRowIndexToModel(selectedRows[1]);

        String player1Name = (String) playerTable.getValueAt(modelRow1, 0);
        String player2Name = (String) playerTable.getValueAt(modelRow2, 0);

        if (player1Name.equals(player2Name)) {
            JOptionPane.showMessageDialog(null, "Please select two different players to compare.");
            return;
        }

        if (playerCompareController != null) {
            playerCompareController.execute(player1Name, player2Name);
        } else {
            JOptionPane.showMessageDialog(null, "Controller is not initialized.");
        }
    }

    /**
     * Loads all players into the table.
     */
    private void loadAllPlayers() {
        tableModel.setRowCount(0);
        InMemoryPlayerDataAccessObject dao = new InMemoryPlayerDataAccessObject();
        List<PlayerData> playerDataList = dao.getAllPlayers();

        for (PlayerData playerData : playerDataList) {
            tableModel.addRow(new Object[]{
                    playerData.getPlayerName(),
                    playerData.getPosition(),
                    playerData.getTeam(),
                    String.valueOf(playerData.getPoints()),
                    String.valueOf(playerData.getTurnovers()),
                    String.valueOf(playerData.getSteals()),
            });
        }
    }

    /**
     * Returns the name of the view.
     *
     * @return the name of the view
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the player compare controller for this view.
     *
     * @param playerCompareController the player compare controller
     */
    public void setPlayerCompareController(PlayerCompareController playerCompareController) {
        this.playerCompareController = playerCompareController;
    }
}