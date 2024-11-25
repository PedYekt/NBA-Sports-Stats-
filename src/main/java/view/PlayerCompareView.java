package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * The PlayerCompareView class represents the GUI for comparing two players' statistics.
 */
public class PlayerCompareView {

    private static final int PLAYER1_COLUMN_INDEX = 1;
    private static final int PLAYER2_COLUMN_INDEX = 2;
    private static final int SEARCH_FIELD_SIZE = 15;

    private JFrame frame;
    private JTable table;
    private JTextField player1Search;
    private JTextField player2Search;

    /**
     * Constructs the PlayerCompareView and initializes the GUI components.
     */
    public PlayerCompareView() {
        frame = new JFrame();
        frame.setTitle("Player Compare");

        // Sample data
        final String[][] data = {
                {"Games Played", "73", "60"},
                {"Points", "30.4", "19.9"},
                {"Assists", "6.5", "6.1"},
                {"Rebounds", "11.5", "8.2"},
                {"Steals", "1.2", "1.3"},
                {"Blocks", "1.1", "1.5"},
                {"Field Goal %", "61.1", "47.5"},
                {"3 Point %", "27.4", "34.1"},
        };

        final String[] columnNames = {"Stat", "Giannis Antetokounmpo", "Scottie Barnes"};

        // Create table model and table
        final DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        // Set custom renderer for player columns
        table.getColumnModel().getColumn(PLAYER1_COLUMN_INDEX).setCellRenderer(new BoldRenderer());
        table.getColumnModel().getColumn(PLAYER2_COLUMN_INDEX).setCellRenderer(new BoldRenderer());

        // Center the text in the first column
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // Create search fields
        player1Search = new JTextField(SEARCH_FIELD_SIZE);
        player2Search = new JTextField(SEARCH_FIELD_SIZE);

        // Add key listeners for search functionality
        player1Search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(PLAYER1_COLUMN_INDEX, player1Search.getText());
            }
        });

        player2Search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(PLAYER2_COLUMN_INDEX, player2Search.getText());
            }
        });

        // Create a panel for search fields
        final JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Player 1:"));
        searchPanel.add(player1Search);
        searchPanel.add(new JLabel("Search Player 2:"));
        searchPanel.add(player2Search);

        // Create a home button
        final JButton homeButton = new JButton("Home");
        homeButton.addActionListener(actionEvent -> {
            // Implement the action to return to the main view
            JOptionPane.showMessageDialog(null, "Returning to main view...");
        });

        // Create a panel for the home button
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(homeButton);

        // Set layout and add components
        frame.setLayout(new BorderLayout());
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Custom renderer to bold the larger number in the player columns and center the text.
     */
    private static final class BoldRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Center the text
            setHorizontalAlignment(SwingConstants.CENTER);

            // Reset font to default
            c.setFont(table.getFont());

            // Get values from both player columns
            final String value1 = (String) table.getValueAt(row, PLAYER1_COLUMN_INDEX);
            final String value2 = (String) table.getValueAt(row, PLAYER2_COLUMN_INDEX);

            try {
                // Parse values as doubles
                final double num1 = Double.parseDouble(value1);
                final double num2 = Double.parseDouble(value2);

                // Bold the larger number
                if ((column == PLAYER1_COLUMN_INDEX && num1 > num2)
                        ||
                        (column == PLAYER2_COLUMN_INDEX && num2 > num1)) {
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }
            }
            catch (NumberFormatException ex) {
                // If values are not numbers, do not apply bold styling
                // Optionally log the exception or handle it as needed
            }

            return c;
        }
    }

    /**
     * Filters the table based on the search query for a given column index.
     *
     * @param columnIndex the index of the column to filter
     * @param query       the search query string
     */
    private void filterTable(int columnIndex, String query) {
        final TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, columnIndex));
    }

    /**
     * The main method to run the PlayerCompareView application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PlayerCompareView::new);
    }
}
