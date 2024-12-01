package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team.ViewTeamController;
import interface_adapter.team.ViewTeamViewModel;

/**
 * The View for when the user is fetching all teams data, sorting according to column header and searching teams.
 */
public class TeamView extends JPanel {

    private final JButton homescreen = new JButton("Back to menu");
    private ViewTeamController viewTeamController;
    private final ViewTeamViewModel viewTeamViewModel;
    private final String viewName = "Teams Data";

    public TeamView(ViewTeamViewModel viewModel) {
        this.viewTeamViewModel = viewModel;

        this.setLayout(new BorderLayout());

        final JLabel title = new JLabel(viewTeamViewModel.TITLE_LABEL, SwingConstants.CENTER);
        final int size = 20;
        title.setFont(new Font("Arial", Font.BOLD, size));
        this.add(title, BorderLayout.NORTH);

        final InMemoryTeamDataAccessObject inMemoryTeamDataAccessObject = new InMemoryTeamDataAccessObject();
        final List<TeamData> teamDataList1 = inMemoryTeamDataAccessObject.getAllTeams();
        final List<String> columnNames = new ArrayList<>(Arrays.asList("Team Name", "Conference", "City", "Points",
                "Turnovers", "Steals"));
        final List<ArrayList<Object>> data = new ArrayList<>();
        for (TeamData teamData : teamDataList1) {
            final ArrayList<Object> list = new ArrayList<>();
            list.add(teamData.getTeamName());
            list.add(teamData.getConference());
            list.add(teamData.getCity());
            list.add(teamData.getPoints());
            list.add(teamData.getTurnovers());
            list.add(teamData.getSteals());
            data.add(list);
        }

        final String[] finalColumnNames = columnNames.toArray(new String[0]);
        final Object[][] finalData = data.stream()
                .map(list -> list.toArray(new Object[0]))
                .toArray(Object[][]::new);

        // Create the table model and JTable
        final DefaultTableModel guiTeamModel = new DefaultTableModel(finalData, finalColumnNames);

        final JTable table = new JTable(guiTeamModel);

        // Back Button
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(homescreen);

        homescreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewTeamController.switchToMenuView();
            }
        });
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Enable sorting and filtering with TableRowSorter
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(guiTeamModel);
        table.setRowSorter(sorter);

        // Add search bar
        final JPanel searchPanel = new JPanel(new BorderLayout());
        final JTextField searchField = new JTextField();
        final JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Search button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String text = searchField.getText();
                if (text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                }
                else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Add mouse listener for toggle sorting behavior
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the clicked column
                final int column = table.columnAtPoint(e.getPoint());
                final List<RowSorter.SortKey> sortKeys = new ArrayList<>(sorter.getSortKeys());
                RowSorter.SortKey existingKey = null;
                // Find if the column is already sorted
                for (RowSorter.SortKey key : sortKeys) {
                    if (key.getColumn() == column) {
                        existingKey = key;
                        break;
                    }
                }
                if (existingKey == null) {
                    // First click: sort ascending
                    sortKeys.add(0, new RowSorter.SortKey(column, SortOrder.ASCENDING));
                }
                else if (existingKey.getSortOrder() == SortOrder.ASCENDING) {
                    // Second click: sort descending
                    sortKeys.set(sortKeys.indexOf(existingKey), new RowSorter.SortKey(column, SortOrder.DESCENDING));
                }
                else {
                    // Third click: cancel sorting for the column
                    sortKeys.remove(existingKey);
                }
                // Apply the updated sort keys
                sorter.setSortKeys(sortKeys);
            }
        });

        // Add the table to a JScrollPane for scroll functionality
        final JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);
    }

    public void setTeamController(ViewTeamController viewTeamController) {
        this.viewTeamController = viewTeamController;
    }

    public String getViewName() {
        return viewName;
    }
}
