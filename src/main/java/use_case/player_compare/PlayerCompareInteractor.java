package use_case.player_compare;

import interface_adapter.player_compare.PlayerComparePresenter;

/**
 * The PlayerCompare use case interactor.
 */
public class PlayerCompareInteractor {

    private final PlayerComparePresenter playerComparePresenter;

    public PlayerCompareInteractor(PlayerComparePresenter playerComparePresenter) {
        this.playerComparePresenter = playerComparePresenter;
    }

    /**
     * Switch to the menu view.
     */
    public void switchToMenuView() {
        playerComparePresenter.switchToMenuView();
    }

        /**
         * Execute the player compare use case.
         * @param playerCompareInputData the input data for the player compare use case
         */
        public void execute (PlayerCompareInputData playerCompareInputData){
            final PlayerCompareOutputData playerCompareOutputData = new PlayerCompareOutputData(false,
                    playerCompareInputData.getPlayer1(), playerCompareInputData.getPlayer2());
            playerComparePresenter.prepareSuccessView(playerCompareOutputData);
        }
    }
