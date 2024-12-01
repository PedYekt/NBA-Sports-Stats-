package interface_adapter.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import api.PlayerApi;
import entity.PlayerData;
import entity.TeamData;
import interface_adapter.player.PlayerDataParser;

/**
 * Converts team data from JSON format.
 */
public class TeamDataConverter {

    private final Map<String, String> teamNameMapping;

    /**
     * Constructor.
     */
    public TeamDataConverter() {
        teamNameMapping = new HashMap<>();
        teamNameMapping.put("BKN", "BRK");
        teamNameMapping.put("CHA", "CHO");
        teamNameMapping.put("PHX", "PHO");
    }

    /**
     * Converts a single team JSON object to a TeamData object.
     *
     * @param teamObject the JSON object containing team data
     * @return a TeamData object
     */
    public TeamData convertTeamData(JSONObject teamObject) {
        try {
            final String conference = teamObject.optString("conference");
            final String division = teamObject.optString("division");
            final String city = teamObject.optString("city");

            // Skip teams with missing data
            if (conference.isEmpty() || division.isEmpty() || city.isEmpty()) {
                return null;
            }

            final String abbreviation = teamObject.optString("abbreviation");
            final String playerApiAbbreviation = teamNameMapping.getOrDefault(abbreviation, abbreviation);

            final TeamData teamData = new TeamData(
                    teamObject.optString("name"),
                    conference,
                    division,
                    city,
                    0, 0, 0
            );

            // Fetch and parse player data for the team
            final String playerJsonString = PlayerApi.fetchAllPlayersData();
            final PlayerDataParser playerDataParser = new PlayerDataParser();
            final List<PlayerData> playerDataList = playerDataParser.fetchAndParseAllPlayers(playerJsonString);

            // Compute scores manually for the team
            final int totalPoints = getTotalPoints(playerDataList, playerApiAbbreviation);
            final int totalTurnovers = getTotalTurnovers(playerDataList, playerApiAbbreviation);
            final int totalSteals = getTotalSteals(playerDataList, playerApiAbbreviation);

            teamData.setPoints(totalPoints);
            teamData.setTurnovers(totalTurnovers);
            teamData.setSteals(totalSteals);

            return teamData;
        }
        catch (JSONException jsonException) {
            System.err.println("Invalid JSON format: " + jsonException.getMessage());
            return null;
        }
    }

    private static int getTotalPoints(List<PlayerData> playerDataList, String playerApiAbbreviation) {
        int totalPoints = 0;
        for (PlayerData playerData : playerDataList) {
            if (playerData.getTeam().equals(playerApiAbbreviation)) {
                totalPoints += playerData.getPoints();
            }
        }
        return totalPoints;
    }

    private static int getTotalTurnovers(List<PlayerData> playerDataList, String playerApiAbbreviation) {
        int totalTurnovers = 0;
        for (PlayerData playerData : playerDataList) {
            if (playerData.getTeam().equals(playerApiAbbreviation)) {
                totalTurnovers += playerData.getTurnovers();
            }
        }
        return totalTurnovers;
    }

    private static int getTotalSteals(List<PlayerData> playerDataList, String playerApiAbbreviation) {
        int totalSteals = 0;
        for (PlayerData playerData : playerDataList) {
            if (playerData.getTeam().equals(playerApiAbbreviation)) {
                totalSteals += playerData.getSteals();
            }
        }
        return totalSteals;
    }
}
