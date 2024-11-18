package view;

import api.PlayerApi;
import entity.PlayerData;
import interface_adapter.player.PlayerDataParser;
import interface_adapter.player.PlayerViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerView extends JPanel {

    private final PlayerViewModel playerViewModel;

    public PlayerView(PlayerViewModel playerViewModel) {
        this.playerViewModel = playerViewModel;

        final JLabel title = new JLabel(PlayerViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> guiPlayerList = new DefaultListModel<>();

        final String jsonString = PlayerApi.fetchAllPlayersData();

        final PlayerDataParser parser = new PlayerDataParser();
        final List<PlayerData> playerDataList = parser.fetchAndParseAllPlayers(jsonString);
        final List<String> playerNames = new ArrayList<>();

        for (PlayerData playerData : playerDataList) {
            if (playerNames.contains(playerData.getPlayerName())) {
                continue;
            }
            else {
                String playerString = new String("Name: " + playerData.getPlayerName() + "," + " Position: " + playerData.getPosition() + "," + " Age: " + playerData.getAge() + "," + " Games: " + playerData.getGames() + "," + " Games Started: " + playerData.getGamesStarted() + "," + " Minutes Per Game: " + playerData.getMinutesPg() + "," + " Field Goals: " + playerData.getFieldGoals() + "," + " Field Goal Attempts: " + playerData.getFieldAttempts() + "," + " Field Goal Percentage: " + playerData.getFieldPercent() + "," + " Three Point Field Goals: " + playerData.getThreeFg() + "," + " Three Point Attempts: " + playerData.getThreeAttempts() + "," + " Three Point Percentage: " + playerData.getThreePercent() + "," + " Two Point Field Goals: " + playerData.getTwoFg() + "," + " Two Point Attempts: " + playerData.getTwoAttempts() + "," + " Two Point Percentage: " + playerData.getTwoPercent() + "," + " Effective Field Goal Percentage: " + playerData.getEffectFgPercent() + "," + " Free Throws: " + playerData.getFt() + "," + " Free Throw Attempts: " + playerData.getFtAttempts() + "," + " Free Throw Percentage: " + playerData.getFtPercent() + "," + " Offensive Rebounds: " + playerData.getOffensiveRb() + "," + " Defensive Rebounds: " + playerData.getDefensiveRb() + "," + " Total Rebounds: " + playerData.getTotalRb() + "," + " Assists: " + playerData.getAssists() + "," + " Steals: " + playerData.getSteals() + "," + " Blocks: " + playerData.getBlocks() + "," + " Turnovers: " + playerData.getTurnovers() + "," + " Personal Fouls: " + playerData.getPersonalFouls() + "," + " Points: " + playerData.getPoints() + "," + " Team: " + playerData.getTeam() + "," + " Season: " + playerData.getSeason());
                guiPlayerList.addElement(playerString);
                playerNames.add(playerData.getPlayerName());
            }
        }

        JList<String> playerList = new JList<>(guiPlayerList);

        JScrollPane playerListScrollPane = new JScrollPane(playerList);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(playerListScrollPane);
    }

}
