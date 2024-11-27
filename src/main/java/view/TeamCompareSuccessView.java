package view;

import data_access.InMemoryTeamDataAccessObject;
import entity.TeamData;
import interface_adapter.team_compare.TeamCompareSuccessState;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;

public class TeamCompareSuccessView extends JPanel implements PropertyChangeListener {
    private final String viewName = "team compare success";
    private final String[] columnNames = new String[]{"Team", "Conference", "Division", "Points", "Turnovers", "Steals"};

    private final TeamCompareSuccessViewModel teamCompareSuccessViewModel;
    private final InMemoryTeamDataAccessObject teams = new InMemoryTeamDataAccessObject();
    private final List<TeamData> teamList;
    private TeamData correctTeam1;
    private TeamData correctTeam2;

    public TeamCompareSuccessView(TeamCompareSuccessViewModel teamCompareSuccessViewModel) {
        this.teamCompareSuccessViewModel = teamCompareSuccessViewModel;
        this.teamCompareSuccessViewModel.addPropertyChangeListener(this);
        this.teamList = teams.getAllTeams();
    }

    public String getViewName() {
        return viewName;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        final TeamCompareSuccessState state = (TeamCompareSuccessState) evt.getNewValue();

        for (TeamData team : teamList) {
            if (Objects.equals(team.getTeamName(), state.getTeam1())) {
                correctTeam1 = new TeamData(team.getTeamName(), team.getConference(), team.getDivision(), team.getCity(), team.getPoints(), team.getTurnovers(), team.getSteals());
            }
            else if (Objects.equals(team.getTeamName(), state.getTeam2())) {
                correctTeam2 = new TeamData(team.getTeamName(), team.getConference(), team.getDivision(), team.getCity(), team.getPoints(), team.getTurnovers(), team.getSteals());
            }
        }
        String[][] data = new String[][]{
                {correctTeam1.getTeamName(), correctTeam1.getConference(), correctTeam1.getDivision(), String.valueOf(correctTeam1.getPoints()), String.valueOf(correctTeam1.getTurnovers()), String.valueOf(correctTeam1.getSteals())},
                {correctTeam2.getTeamName(), correctTeam2.getConference(), correctTeam2.getDivision(), String.valueOf(correctTeam2.getPoints()), String.valueOf(correctTeam2.getTurnovers()), String.valueOf(correctTeam2.getSteals())}};

        final JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        final JScrollPane sp = new JScrollPane(table);

        this.add(sp);
    }
}
