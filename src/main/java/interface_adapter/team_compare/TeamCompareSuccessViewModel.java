package interface_adapter.team_compare;

import interface_adapter.ViewModel;

public class TeamCompareSuccessViewModel extends ViewModel<TeamCompareSuccessState> {

    public static final String TITLE_LABEL = "TEAM COMPARISON";
    public static final String TEAM1_LABEL = "Team 1";
    public static final String TEAM2_LABEL = "Team 2";
    public static final String COMPARE_BUTTON_LABEL = "Compare";

    public TeamCompareSuccessViewModel() {
        super("team compare success");
        setState(new TeamCompareSuccessState());
    }

}
