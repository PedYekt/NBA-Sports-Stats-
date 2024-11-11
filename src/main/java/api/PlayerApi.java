package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * PlayerDataAccessObject class to handle player data access.
 */
public class PlayerApi {

    PlayerApi() {
        // Default constructor
    }

    /**
     * Fetch all players data.
     * @return JSON string of all players data
     */
    public static String fetchAllPlayersData() {
        String playerDataString = "";

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final Request request = new Request.Builder()
                .url(Constants.BASE_URL + Constants.PLAYER_DATA_ADVANCED_PLAYOFFS_SEASON
                        + "/" + Constants.PLAYER_DATA_ADVANCED_PLAYOFFS_SEASON_YEAR)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                playerDataString = response.body().string();
            }
            else {
                throw new IOException("Unexpected code " + response);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return playerDataString;
    }
}
