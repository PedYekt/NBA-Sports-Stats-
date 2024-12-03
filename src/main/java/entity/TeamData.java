package entity;

/**
 * Team data entity.
 */
public class TeamData {

    private String teamName;

    private String conference;

    private String division;

    private String city;

    private int points;

    private int turnovers;

    private int steals;

    /**
     * Constructor.
     * @param teamName team name
     * @param conference conference
     * @param division division
     * @param city city
     * @param points points
     * @param turnovers turnovers
     * @param steals steals
     */
    public TeamData(String teamName, String conference, String division, String city,
                    int points, int turnovers, int steals) {
        this.teamName = teamName;
        this.conference = conference;
        this.division = division;
        this.city = city;
        this.points = points;
        this.turnovers = turnovers;
        this.steals = steals;
    }

    /**
     * Get team name.
     * @return team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Get conference.
     * @return conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * Get division.
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Get city.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get points.
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Get turnovers.
     * @return turnovers
     */
    public int getTurnovers() {
        return turnovers;
    }

    /**
     * Get steals.
     * @return steals
     */
    public int getSteals() {
        return steals;
    }

    /**
     * Set team name.
     * @param teamName team name
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Set conference.
     * @param conference conference
     */
    public void setConference(String conference) {
        this.conference = conference;
    }

    /**
     * Set division.
     * @param division division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Set city.
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Set points.
     * @param points points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Set turnovers.
     * @param turnovers turnovers
     */
    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    /**
     * Set steals.
     * @param steals steals
     */
    public void setSteals(int steals) {
        this.steals = steals;
    }
}
