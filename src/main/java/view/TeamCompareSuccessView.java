package view;

import interface_adapter.team_compare.TeamCompareSuccessState;
import interface_adapter.team_compare.TeamCompareSuccessViewModel;
import interface_adapter.team_compare.TeamCompareViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TeamCompareSuccessView extends JPanel implements PropertyChangeListener {
    private final String viewName = "team compare success";
    private final String[] columnNames = new String[]{"Team", "Conference", "Division", "Points", "Turnovers", "Steals"};

    private final TeamCompareSuccessViewModel teamCompareSuccessViewModel;

    public TeamCompareSuccessView(TeamCompareSuccessViewModel teamCompareSuccessViewModel) {
        this.teamCompareSuccessViewModel = teamCompareSuccessViewModel;
        this.teamCompareSuccessViewModel.addPropertyChangeListener(this);
    }

    public String getViewName() {
        return viewName;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        final TeamCompareSuccessState state = (TeamCompareSuccessState) evt.getNewValue();
        String[][] data = new String[][]{
                {state.getTeam1(), "filler", "filler", "filler", "filler", "filler"},
                {state.getTeam2(), "filler", "filler", "filler", "filler", "filler"}};

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
