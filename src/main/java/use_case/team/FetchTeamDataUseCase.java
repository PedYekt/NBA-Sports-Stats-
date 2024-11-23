package use_case.team;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import api.TeamApi;
import entity.TeamData;
import interface_adapter.team.TeamDataConverter;

/**
 * FetchTeamDataUseCase class to fetch team data.
 */
public class FetchTeamDataUseCase {


    private final TeamDataConverter teamDataConverter;

    public FetchTeamDataUseCase() {
        // Default constructor
        this.teamDataConverter = new TeamDataConverter();
    }

    /**
     * Fetch and return team data.
     * @return list of team data
     */
    public List<TeamData> fetchAndReturnTeamData() {
        final List<TeamData> teamDataList = new ArrayList<>();
        try {
            final String jsonString = TeamApi.fetchAllTeamsData();
            final JSONObject jsonObject = new JSONObject(jsonString);
            final JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject teamObject = jsonArray.getJSONObject(i);
                final TeamData teamData = this.teamDataConverter.convertTeamData(teamObject);
                if (teamData != null) {
                    teamDataList.add(teamData);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return teamDataList;
    }

    public static void main(String[] args) {
        final FetchTeamDataUseCase fetchTeamDataUseCase = new FetchTeamDataUseCase();
        final List<TeamData> teamDataList = fetchTeamDataUseCase.fetchAndReturnTeamData();
        for (TeamData teamData : teamDataList) {
            System.out.println(teamData.getTeamName());
        }
    }
}
