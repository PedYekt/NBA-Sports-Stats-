package interface_adapter.team_compare;

/**
 * The team compare use case/view's state.
 */
public class TeamCompareState {

    private String team1;
    private String team2;
    private String teamError;

    public String getTeam1() {
        return team1;
    }

    public String getTeamError() {
        return teamError;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeamError(String teamError) {
        this.teamError = teamError;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "TeamCompareState{"
                + "team1='" + team1 + '\''
                + ", team2='" + team2 + '\''
                + ", teamError='" + teamError + '\''
                + '}';
    }
}
