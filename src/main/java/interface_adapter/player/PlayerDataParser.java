package interface_adapter.player;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.PlayerData;

/**
 * PlayerDataParser class to parse player data.
 */
public class PlayerDataParser {

    /**
     * Fetch and parse all players from JSON string.
     * @param jsonString JSON string
     * @return list of player data
     */
    public List<PlayerData> fetchAndParseAllPlayers(String jsonString) {
        final List<PlayerData> playerDataList = new ArrayList<>();
        final JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final PlayerData playerData = new PlayerData(
                    jsonObject.optInt("id"),
                    jsonObject.optString("playerName"),
                    jsonObject.optString("position"),
                    jsonObject.optInt("age"),
                    jsonObject.optInt("games"),
                    jsonObject.optInt("gamesStarted"),
                    jsonObject.optDouble("minutesPg"),
                    jsonObject.optInt("fieldGoals"),
                    jsonObject.optInt("fieldAttempts"),
                    jsonObject.optDouble("fieldPercent"),
                    jsonObject.optInt("threeFg"),
                    jsonObject.optInt("threeAttempts"),
                    jsonObject.optDouble("threePercent"),
                    jsonObject.optInt("twoFg"),
                    jsonObject.optInt("twoAttempts"),
                    jsonObject.optDouble("twoPercent"),
                    jsonObject.optDouble("effectFgPercent"),
                    jsonObject.optInt("ft"),
                    jsonObject.optInt("ftAttempts"),
                    jsonObject.optDouble("ftPercent"),
                    jsonObject.optInt("offensiveRb"),
                    jsonObject.optInt("defensiveRb"),
                    jsonObject.optInt("totalRb"),
                    jsonObject.optInt("assists"),
                    jsonObject.optInt("steals"),
                    jsonObject.optInt("blocks"),
                    jsonObject.optInt("turnovers"),
                    jsonObject.optInt("personalFouls"),
                    jsonObject.optInt("points"),
                    jsonObject.optString("team"),
                    jsonObject.optInt("season"),
                    jsonObject.optString("playerId")
            );
            playerDataList.add(playerData);
        }

        return playerDataList;
    }

}
