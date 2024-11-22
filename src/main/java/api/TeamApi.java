package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * TeamApi class to handle team data access.
 */
public class TeamApi {

    public TeamApi() {
        // Default constructor
    }

    /**
     * Fetch all teams data.
     * @return JSON string of all teams data
     */
    public static String fetchAllTeamsData() {
        String teamDataString = "";

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final Request request = new Request.Builder()
                .url("https://api.balldontlie.io/v1/teams")
                .get()
                .addHeader("Authorization", "086f38b6-4a25-4da8-b5d2-11d8cf9fc915")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                teamDataString = response.body().string();
            }
            else {
                throw new IOException("Unexpected code " + response);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return teamDataString;
    }

    public static void main(String[] args) {
        System.out.println(fetchAllTeamsData());
    }
}
