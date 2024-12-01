package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team_compare.TeamCompareController;
import interface_adapter.team_compare.TeamCompareSuccessState;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;

/**
 * The Success View for comparing two teams.
 */
public class TeamCompareSuccessView extends JPanel implements PropertyChangeListener {
    private final String viewName = "team compare success";
    private final String[] columnNames = new String[]{"Stats", "Team One", "Team Two"};
    private JScrollPane sp;

    private final TeamCompareSuccessViewModel teamCompareSuccessViewModel;
    private TeamCompareController teamCompareController;
    private final InMemoryTeamDataAccessObject teams = new InMemoryTeamDataAccessObject();
    private final List<TeamData> teamList;
    private TeamData correctTeam1;
    private TeamData correctTeam2;

    public TeamCompareSuccessView(TeamCompareSuccessViewModel teamCompareSuccessViewModel) {
        this.teamCompareSuccessViewModel = teamCompareSuccessViewModel;
        this.teamCompareSuccessViewModel.addPropertyChangeListener(this);
        this.teamList = teams.getAllTeams();

        final JButton menu = new JButton("Back to Menu");
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                teamCompareController.switchToMenuView();
            }
        });

        this.add(menu);
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * React to property change being fired and use state information to build JTable.
     * @param evt the ActionEvent to react to
     */
    public void propertyChange(PropertyChangeEvent evt) {
        final TeamCompareSuccessState state = (TeamCompareSuccessState) evt.getNewValue();

        for (TeamData team : teamList) {
            if (Objects.equals(team.getTeamName(), state.getTeam1()) && Objects.equals(team.getTeamName(),
                    state.getTeam2())) {
                JOptionPane.showMessageDialog(null, "Cannot compare same teams");
            }
            else if (Objects.equals(team.getTeamName(), state.getTeam1())) {
                correctTeam1 = new TeamData(team.getTeamName(), team.getConference(), team.getDivision(),
                        team.getCity(), team.getPoints(), team.getTurnovers(), team.getSteals());
            }
            else if (Objects.equals(team.getTeamName(), state.getTeam2())) {
                correctTeam2 = new TeamData(team.getTeamName(), team.getConference(), team.getDivision(),
                        team.getCity(), team.getPoints(), team.getTurnovers(), team.getSteals());
            }
        }

        final String[][] data = new String[][]{
                {"Name", correctTeam1.getTeamName(), correctTeam2.getTeamName()},
                {"Conference", correctTeam1.getConference(), correctTeam2.getConference()},
                {"Division", correctTeam1.getDivision(), correctTeam2.getDivision()},
                {"Points", String.valueOf(correctTeam1.getPoints()), String.valueOf(correctTeam2.getPoints())},
                {"Turnovers", String.valueOf(correctTeam1.getTurnovers()), String.valueOf(correctTeam2.getTurnovers())},
                {"Steals", String.valueOf(correctTeam1.getSteals()), String.valueOf(correctTeam2.getSteals())},
        };

        final JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        if (sp != null) {
            this.remove(sp);
        }

        sp = new JScrollPane(table);
        this.add(sp);
    }

    public void setTeamCompareController(TeamCompareController teamCompareController) {
        this.teamCompareController = teamCompareController;
    }
}
