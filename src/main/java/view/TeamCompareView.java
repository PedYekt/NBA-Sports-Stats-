package view;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team_compare.TeamCompareController;
import interface_adapter.team_compare.TeamCompareState;
import interface_adapter.team_compare.TeamCompareViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TeamCompareView extends JPanel {
    private final String viewName = "team compare";
    private JComboBox<String> dropdown1;
    private JComboBox<String> dropdown2;

    private final TeamCompareViewModel teamCompareViewModel;
    private TeamCompareController teamCompareController;

    private final JButton compare;

    public TeamCompareView(TeamCompareViewModel teamCompareViewModel) {
        this.teamCompareViewModel = teamCompareViewModel;
        //teamCompareViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(TeamCompareViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        compare = new JButton(TeamCompareViewModel.COMPARE_BUTTON_LABEL);
        compare.setAlignmentX(Component.CENTER_ALIGNMENT);

//        final InMemoryTeamDataAccessObject teams = new InMemoryTeamDataAccessObject();
//        final List<TeamData> teamList = teams.getAllTeams();
//        DefaultListModel<String> teamNames = new DefaultListModel<>();
//
//        for (TeamData team : teamList) {
//            teamNames.addElement(team.getTeamName());
//        }


        String[] teams = {"Toronto Raptors", "Denver Nuggets", "Golden State Warriors", "Memphis Grizzlies"};
        dropdown1 = new JComboBox(teams);
        dropdown2 = new JComboBox(teams);
        dropdown1.setEditable(true);
        dropdown2.setEditable(true);

        compare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                teamCompareController.execute((String) dropdown1.getSelectedItem(),
                        (String) dropdown2.getSelectedItem());
                //teamCompareController.switchToTeamCompareSuccessView();

            }
        });

//        JList<String> finalList = new JList<>(teamNames);
//        JScrollPane teamsListScrollPane = new JScrollPane(finalList);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(dropdown1);
        this.add(dropdown2);
        this.add(compare);

    }

    public String getViewName() {
        return viewName;
    }

    public void setTeamCompareController(TeamCompareController teamCompareController) {
        this.teamCompareController = teamCompareController;
    }

}
