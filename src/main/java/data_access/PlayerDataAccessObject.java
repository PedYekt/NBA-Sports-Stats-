package data_access;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * PlayerDataAccessObject class to handle player data access.
 */
public class PlayerDataAccessObject {

    /**
     * Main method to execute the data access.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("http://b8c40s8.143.198.70.30.sslip.io/api/PlayerDataTotals/name/LeBron James")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Response Code: " + response.code());
                System.out.println("Response Body: " + response.body().string());
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}