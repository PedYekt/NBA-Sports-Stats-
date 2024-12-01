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
                    jsonObject.getString("playerName"),
                    jsonObject.getString("position"),
                    jsonObject.getInt("steals"),
                    jsonObject.getInt("blocks"),
                    jsonObject.getInt("turnovers"),
                    jsonObject.getInt("points"),
                    jsonObject.getString("team")
            );
            playerDataList.add(playerData);
        }

        return playerDataList;
    }
}
