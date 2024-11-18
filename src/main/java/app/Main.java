package app;

import api.PlayerApi;
import entity.PlayerData;
import interface_adapter.player.PlayerDataParser;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static java.awt.Component.CENTER_ALIGNMENT;

/**
 * Main class to fetch and display player data.
 */
public class Main {

    /**
     * Main method to fetch and display player data based on user input.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addPlayerView().build();

        application.pack();
        application.setVisible(true);

        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: ");
        final String playerName = scanner.nextLine();

        final String jsonString = PlayerApi.fetchAllPlayersData();
        if (jsonString != null) {
            final PlayerDataParser parser = new PlayerDataParser();
            final List<PlayerData> playerDataList = parser.fetchAndParseAllPlayers(jsonString);

            for (PlayerData playerData : playerDataList) {
                if (playerData.getPlayerName().equalsIgnoreCase(playerName)) {
                    System.out.println(playerData);
                    return;
                }
            }
            System.out.println("Player not found.");
        } else {
            System.out.println("Failed to fetch player data.");
        }
    }
}