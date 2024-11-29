package view;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player.PlayerController;
import interface_adapter.player.PlayerViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class PlayerView extends JPanel {
    private static final int SEARCH_FIELD_COLUMNS = 20;
    private final JTextField searchField;
    private final JButton viewAllButton;
    private final JButton backButton;
    private final JTable playerTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;
    private PlayerController controller;
    private final PlayerViewModel viewModel;

    private final String viewName = "player view";

    public PlayerView(PlayerController controller, PlayerViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new BorderLayout());

        // Create the table model and JTable
        tableModel = new DefaultTableModel(new Object[]{"Player Name", "Points", "Turnovers", "Steals"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        playerTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        playerTable.setRowSorter(sorter);

        // Create the search field
        searchField = new JTextField(SEARCH_FIELD_COLUMNS);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                final String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Create the "View All" button
        viewAllButton = new JButton("View All");
        viewAllButton.addActionListener(event -> {
            searchField.setText("");
            sorter.setRowFilter(null);
            loadAllPlayers();
        });

        // Create a panel for the search field and "View All" button
        final JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(viewAllButton);

        // Create the "Back to Menu" button
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(event -> controller.switchToMenuView());

        // Create a panel for the "Back to Menu" button
        final JPanel backPanel = new JPanel();
        backPanel.add(backButton);

        // Add components to the main panel
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(playerTable), BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        // Load all players initially
        loadAllPlayers();
    }

    private void loadAllPlayers() {
        tableModel.setRowCount(0);
        InMemoryPlayerDataAccessObject dao = new InMemoryPlayerDataAccessObject();
        List<PlayerData> playerDataList = dao.getAllPlayers();

        for (PlayerData playerData : playerDataList) {
            tableModel.addRow(new Object[]{
                playerData.getPlayerName(),
                String.valueOf(playerData.getPoints()),
                String.valueOf(playerData.getTurnovers()),
                String.valueOf(playerData.getSteals())
            });
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setPlayerController(PlayerController controller) {
        this.controller = controller;
    }
}