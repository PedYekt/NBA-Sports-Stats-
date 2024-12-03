package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.TeamData;
import entity.TeamRepository;
import use_case.view_team.FetchTeamDataUseCase;

/**
 * In-memory implementation of the TeamRepository interface.
 */
public class InMemoryTeamDataAccessObject implements TeamRepository {
    private final List<TeamData> teamDataList;

    private InMemoryTeamDataAccessObject(Builder builder) {
        this.teamDataList = builder.teamDataList;
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

    public static class Builder {
        private final List<TeamData> teamDataList;

        public Builder() {
            this.teamDataList = new ArrayList<>();
        }

        /**
         * Fetches team data from the use case and adds it to the list.
         *
         * @return the Builder
         */
        public Builder fetchTeamData() {
            final FetchTeamDataUseCase fetchTeamDataUseCase = new FetchTeamDataUseCase();
            teamDataList.addAll(fetchTeamDataUseCase.fetchAndReturnTeamData());
            return this;
        }

        /**
         * Builds the InMemoryTeamDataAccessObject.
         *
         * @return the InMemoryTeamDataAccessObject
         */
        public InMemoryTeamDataAccessObject build() {
            return new InMemoryTeamDataAccessObject(this);
        }
    }
}