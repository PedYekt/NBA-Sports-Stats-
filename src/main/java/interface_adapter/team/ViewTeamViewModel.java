package interface_adapter.team;

import interface_adapter.ViewModel;

/**
 * The team view model of the ViewTeam use case.
 */
public class ViewTeamViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Teams Data";

    public ViewTeamViewModel() {
        super("Teams Data");
    }
}
