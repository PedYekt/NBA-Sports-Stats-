package entity;

/**
 * PlayerData class represents a player with various statistics.
 */
public class PlayerData {
    private final int id;
    private final String playerName;
    private final String position;
    private final int age;
    private final int games;
    private final int gamesStarted;
    private final double minutesPg;
    private final int fieldGoals;
    private final int fieldAttempts;
    private final double fieldPercent;
    private final int threeFg;
    private final int threeAttempts;
    private final double threePercent;
    private int twoFg;
    private int twoAttempts;
    private double twoPercent;
    private double effectFgPercent;
    private int ft;
    private int ftAttempts;
    private double ftPercent;
    private int offensiveRb;
    private int defensiveRb;
    private int totalRb;
    private int assists;
    private int steals;
    private int blocks;
    private int turnovers;
    private int personalFouls;
    private int points;
    private String team;
    private int season;
    private String playerId;

    public PlayerData(int id, String playerName, String position, int age, int games, int gamesStarted, double minutesPg, int fieldGoals, int fieldAttempts, double fieldPercent, int threeFg, int threeAttempts, double threePercent, int twoFg, int twoAttempts, double twoPercent, double effectFgPercent, int ft, int ftAttempts, double ftPercent, int offensiveRb, int defensiveRb, int totalRb, int assists, int steals, int blocks, int turnovers, int personalFouls, int points, String team, int season, String playerId) {
        this.id = id;
        this.playerName = playerName;
        this.position = position;
        this.age = age;
        this.games = games;
        this.gamesStarted = gamesStarted;
        this.minutesPg = minutesPg;
        this.fieldGoals = fieldGoals;
        this.fieldAttempts = fieldAttempts;
        this.fieldPercent = fieldPercent;
        this.threeFg = threeFg;
        this.threeAttempts = threeAttempts;
        this.threePercent = threePercent;
        this.twoFg = twoFg;
        this.twoAttempts = twoAttempts;
        this.twoPercent = twoPercent;
        this.effectFgPercent = effectFgPercent;
        this.ft = ft;
        this.ftAttempts = ftAttempts;
        this.ftPercent = ftPercent;
        this.offensiveRb = offensiveRb;
        this.defensiveRb = defensiveRb;
        this.totalRb = totalRb;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personalFouls = personalFouls;
        this.points = points;
        this.team = team;
        this.season = season;
        this.playerId = playerId;
    }

@Override
public String toString() {
    return "PlayerData{"
            + "id=" + id
            + ", playerName='" + playerName + '\''
            + ", position='" + position + '\''
            + ", age=" + age
            + ", games=" + games
            + ", gamesStarted=" + gamesStarted
            + ", minutesPg=" + minutesPg
            + ", fieldGoals=" + fieldGoals
            + ", fieldAttempts=" + fieldAttempts
            + ", fieldPercent=" + fieldPercent
            + ", threeFg=" + threeFg
            + ", threeAttempts=" + threeAttempts
            + ", threePercent=" + threePercent
            + ", twoFg=" + twoFg
            + ", twoAttempts=" + twoAttempts
            + ", twoPercent=" + twoPercent
            + ", effectFgPercent=" + effectFgPercent
            + ", ft=" + ft
            + ", ftAttempts=" + ftAttempts
            + ", ftPercent=" + ftPercent
            + ", offensiveRb=" + offensiveRb
            + ", defensiveRb=" + defensiveRb
            + ", totalRb=" + totalRb
            + ", assists=" + assists
            + ", steals=" + steals
            + ", blocks=" + blocks
            + ", turnovers=" + turnovers
            + ", personalFouls=" + personalFouls
            + ", points=" + points
            + ", team='" + team + '\''
            + ", season=" + season
            + ", playerId='" + playerId + '\''
            + '}';
}

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getGames() {
        return games;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public double getMinutesPg() {
        return minutesPg;
    }

    public int getFieldGoals() {
        return fieldGoals;
    }

    public int getFieldAttempts() {
        return fieldAttempts;
    }

    public double getFieldPercent() {
        return fieldPercent;
    }

    public int getThreeFg() {
        return threeFg;
    }

    public int getThreeAttempts() {
        return threeAttempts;
    }

    public double getThreePercent() {
        return threePercent;
    }

    public int getTwoFg() {
        return twoFg;
    }

    public int getTwoAttempts() {
        return twoAttempts;
    }

    public double getTwoPercent() {
        return twoPercent;
    }

    public double getEffectFgPercent() {
        return effectFgPercent;
    }

    public int getFt() {
        return ft;
    }

    public int getFtAttempts() {
        return ftAttempts;
    }

    public double getFtPercent() {
        return ftPercent;
    }

    public int getOffensiveRb() {
        return offensiveRb;
    }

    public int getDefensiveRb() {
        return defensiveRb;
    }

    public int getTotalRb() {
        return totalRb;
    }

    public int getAssists() {
        return assists;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public int getPoints() {
        return points;
    }

    public String getTeam() {
        return team;
    }

    public int getSeason() {
        return season;
    }

    public String getPlayerId() {
        return playerId;
    }
}
