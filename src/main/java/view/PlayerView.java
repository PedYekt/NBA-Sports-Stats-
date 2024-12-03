package view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player.PlayerController;
import interface_adapter.player.PlayerViewModel;

/**
 * The view for displaying player data.
 */
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

        // Set custom comparators for numeric columns
        sorter.setComparator(3, Comparator.comparingInt(value -> Integer.parseInt((String) value)));
        sorter.setComparator(4, Comparator.comparingInt(value -> Integer.parseInt((String) value)));
        sorter.setComparator(5, Comparator.comparingInt(value -> Integer.parseInt((String) value)));

        // Make columns non-movable
        final TableColumnModel columnModel = playerTable.getColumnModel();
        columnModel.getColumn(0).setResizable(false);
        columnModel.getColumn(1).setResizable(false);
        columnModel.getColumn(2).setResizable(false);
        columnModel.getColumn(3).setResizable(false);
        columnModel.getColumn(4).setResizable(false);
        columnModel.getColumn(5).setResizable(false);

        // Create the search field
        searchField = new JTextField(SEARCH_FIELD_COLUMNS);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                final String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                }
                else {
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
        final InMemoryPlayerDataAccessObject dao = new
                InMemoryPlayerDataAccessObject
                .Builder()
                .fetchPlayerData()
                .build();
        final List<PlayerData> playerDataList = dao.getAllPlayers();

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
     * Sets the player controller for this view.
     *
     * @param controller the player controller
     */
    public void setPlayerController(PlayerController controller) {
        this.controller = controller;
    }
}