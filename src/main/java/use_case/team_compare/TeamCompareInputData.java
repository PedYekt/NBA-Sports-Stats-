package use_case.team_compare;

/**
 * Input data for the TeamCompare use case.
 */
public class TeamCompareInputData {

    private final String team1;
    private final String team2;

    public TeamCompareInputData(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    String getTeam1() {
        return team1;
    }

    String getTeam2() {
        return team2;
    }
}
