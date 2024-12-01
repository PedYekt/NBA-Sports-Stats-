package use_case.team_compare;

public class TeamCompareOutputData {

    private final boolean useCaseFailed;

    private final String team1;
    private final String team2;

    public TeamCompareOutputData(boolean useCaseFailed, String team1, String team2) {
        this.useCaseFailed = useCaseFailed;
        this.team1 = team1;
        this.team2 = team2;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }
}
