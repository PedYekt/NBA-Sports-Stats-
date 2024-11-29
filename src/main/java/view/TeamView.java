package view;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team.TeamController;
import interface_adapter.team.TeamViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class TeamView extends JPanel {

    private final JButton homescreen = new JButton("Back to menu");
    private TeamController teamController;
    private final TeamViewModel teamViewModel;
    private final String viewName = "Teams Data";

    public TeamView(TeamViewModel viewModel) {
        this.teamViewModel = viewModel;

        this.setLayout(new BorderLayout());

        final JLabel title = new JLabel(teamViewModel.TITLE_LABEL, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title, BorderLayout.NORTH);

        final InMemoryTeamDataAccessObject inMemoryTeamDataAccessObject = new InMemoryTeamDataAccessObject();
        final List<TeamData> teamDataList1 = inMemoryTeamDataAccessObject.getAllTeams();
        List<String> columnNames = new ArrayList<>(Arrays.asList("Team Name", "Conference", "City", "Points",
                "Turnovers", "Steals"));
        List<ArrayList<Object>> data = new ArrayList<>();
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

        DefaultTableModel guiTeamModel = new DefaultTableModel(finalData, finalColumnNames);

        JTable table = new JTable(guiTeamModel);

        // Add the table to a JScrollPane for scroll functionality
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(homescreen);

//        this.setLayout(new java.awt.BorderLayout());
//        this.add(table, java.awt.BorderLayout.CENTER);
//        this.add(scrollPane);
//        this.add(title);

        homescreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                teamController.switchToMenuView();
            }
        });
        this.add(buttonPanel, BorderLayout.SOUTH);
//        this.add(homescreen);
    }

    public void setTeamController(TeamController teamController) {
        this.teamController = teamController;
    }

    public String getViewName() {
        return viewName;
    }
}
