package view;

import data_access.InMemoryPlayerDataAccessObject;
import entity.PlayerData;
import interface_adapter.player.PlayerController;
import interface_adapter.player.PlayerViewModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * PlayerView is a JPanel that displays player data in a table.
 */
public class PlayerView extends JPanel {
    private static final int SEARCH_FIELD_COLUMNS = 20;
    private final JTextField searchField;
    private final JButton viewAllButton;
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

        // Set comparators for numeric columns
        sorter.setComparator(1, (o1, o2) -> Integer.compare(Integer.parseInt((String) o1), Integer.parseInt((String) o2)));
        sorter.setComparator(2, (o1, o2) -> Integer.compare(Integer.parseInt((String) o1), Integer.parseInt((String) o2)));
        sorter.setComparator(3, (o1, o2) -> Integer.compare(Integer.parseInt((String) o1), Integer.parseInt((String) o2)));

        // Create a panel for the search field and button
        final JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Search:"));
        controlPanel.add(searchField);
        controlPanel.add(viewAllButton);

        // Add components to the main panel
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(playerTable), BorderLayout.CENTER);

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

    /**
     * Updates the table with the latest player data.
     */
    public void updateTable() {
        tableModel.setRowCount(0);
        for (PlayerData player : viewModel.getState().getPlayers()) {
            tableModel.addRow(new Object[]{
                    player.getPlayerName(),
                    String.valueOf(player.getPoints()),
                    String.valueOf(player.getTurnovers()),
                    String.valueOf(player.getSteals()),
            });
        }
    }

    /**
     * Returns the name of the view.
     * @return the name of the view
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the player controller.
     * @param controller the player controller
     */
    public void setPlayerController(PlayerController controller) {
        this.controller = controller;
    }

}
