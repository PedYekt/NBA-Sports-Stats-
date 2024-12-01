package use_case.player_compare;

/**
 * The player compare use case's input data.
 */
public class PlayerCompareInputData {

    private final String player1;
    private final String player2;

    public PlayerCompareInputData(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    String getPlayer1() {
        return player1;
    }

    String getPlayer2() {
        return player2;
    }
}
