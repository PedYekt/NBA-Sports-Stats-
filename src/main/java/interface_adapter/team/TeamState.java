package interface_adapter.team;

import java.util.List;

import entity.TeamData;

/**
 * The team state.
 */
public class TeamState {

    private List<TeamData> teams;
    private String error;

    public List<TeamData> getTeams() {
        return teams;
    }

    public String getError() {
        return error;
    }

    public void setTeams(List<TeamData> teams) {
        this.teams = teams;
    }

    /**
     * Present the TeamData object in proper string form.
     * @return the TeamData object in proper string form.
     */
    public String toString() {
        return "TeamState{"
                + "teams=" + teams
                + ", error='" + error + '\''
                + '}';
    }
}
