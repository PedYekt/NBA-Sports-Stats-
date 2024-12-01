package interface_adapter.player_compare;

/**
 * The state for the Player Compare use case success view
 */
public class PlayerCompareSuccessState {

    private String player1;
    private String player2;
    private String playerError;

    public String getPlayer1() {
        return player1;
    }

    public String getPlayerError() {
        return playerError;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayerError(String playerError) {
        this.playerError = playerError;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return "PlayerCompareSuccessState{"
                + "player1='" + player1 + '\''
                + ", player2='" + player2 + '\''
                + ", playerError='" + playerError + '\''
                + '}';
    }
}
