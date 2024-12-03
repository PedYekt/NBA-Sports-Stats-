package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        try {
            playerDataString = new String(Files.readAllBytes(Paths.get("src/main/java/api/playerApi.json")));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return playerDataString;
    }
}
