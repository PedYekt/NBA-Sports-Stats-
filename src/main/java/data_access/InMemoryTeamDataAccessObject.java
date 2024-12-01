package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.TeamData;
import use_case.team.FetchTeamDataUseCase;

/**
 * In-memory implementation of the TeamRepository interface.
 */
public class InMemoryTeamDataAccessObject {
    private final List<TeamData> teamDataList;

    /**
     * Constructor to initialize the in-memory data store.
     */
    public InMemoryTeamDataAccessObject() {
        this.teamDataList = new ArrayList<>();
        populateData();
    }

    /**
     * Populates the in-memory data store with data fetched from the use case.
     */
    private void populateData() {
        final FetchTeamDataUseCase fetchTeamDataUseCase = new FetchTeamDataUseCase();
        teamDataList.addAll(fetchTeamDataUseCase.fetchAndReturnTeamData());
    }

    /**
     * Retrieves all teams from the in-memory data store.
     *
     * @return a list of all team data
     */
    public List<TeamData> getAllTeams() {
        return new ArrayList<>(teamDataList);
    }

    /**
     * Retrieves a team by name from the in-memory data store.
     *
     * @param teamName the name of the team to retrieve
     * @return the team data object
     */
    public TeamData getTeamByName(String teamName) {
        for (TeamData teamData : teamDataList) {
            if (teamData.getTeamName().equalsIgnoreCase(teamName)) {
                return teamData;
            }
        }
        return null;
    }
}
