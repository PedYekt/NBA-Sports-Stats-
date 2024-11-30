package interface_adapter.team_compare;

import interface_adapter.ViewModel;

/**
 * The model for the TeamCompare view.
 */
public class TeamCompareViewModel extends ViewModel<TeamCompareState> {

    public static final String TITLE_LABEL = "TEAM COMPARISON";
    public static final String TEAM1_LABEL = "Team 1";
    public static final String TEAM2_LABEL = "Team 2";
    public static final String COMPARE_BUTTON_LABEL = "Compare";

    public TeamCompareViewModel() {
        super("team compare");
        setState(new TeamCompareState());
    }

}
