package use_case.view_team;

import java.util.List;

import entity.TeamData;

/**
 * Response model for the ViewTeams use case.
 */
public class ViewTeamResponseModel {
    private final List<TeamData> teams;

    public ViewTeamResponseModel(List<TeamData> teams) {
        this.teams = teams;
    }

    public List<TeamData> getTeams() {
        return teams;
    }
}
