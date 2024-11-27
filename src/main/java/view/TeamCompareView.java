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

        final InMemoryTeamDataAccessObject teams = new InMemoryTeamDataAccessObject();
        final List<TeamData> teamList = teams.getAllTeams();
        final DefaultListModel<String> teamNames = new DefaultListModel<>();

        for (TeamData team : teamList) {
            teamNames.addElement(team.getTeamName());
        }

        final String[] teamListFinal = {teamNames.get(0), teamNames.get(1), teamNames.get(2), teamNames.get(3), teamNames.get(4), teamNames.get(5), teamNames.get(6), teamNames.get(7), teamNames.get(8), teamNames.get(9), teamNames.get(10), teamNames.get(11), teamNames.get(12), teamNames.get(13), teamNames.get(14), teamNames.get(15), teamNames.get(16), teamNames.get(17), teamNames.get(18), teamNames.get(19), teamNames.get(20), teamNames.get(21), teamNames.get(22), teamNames.get(23), teamNames.get(24), teamNames.get(25), teamNames.get(26), teamNames.get(27), teamNames.get(28), teamNames.get(29)};
        dropdown1 = new JComboBox(teamListFinal);
        dropdown2 = new JComboBox(teamListFinal);
        dropdown1.setEditable(true);
        dropdown2.setEditable(true);

        compare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                teamCompareController.execute((String) dropdown1.getSelectedItem(),
                        (String) dropdown2.getSelectedItem());

            }
        });

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
