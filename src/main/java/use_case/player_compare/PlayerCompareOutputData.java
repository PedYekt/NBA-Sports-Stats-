package use_case.player_compare;

/**
 * The player compare use case output data.
 */

public class PlayerCompareOutputData {

    private final boolean useCaseFailed;

    private final String player1;
    private final String player2;

    public PlayerCompareOutputData(boolean useCaseFailed, String player1, String player2) {
        this.useCaseFailed = useCaseFailed;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
