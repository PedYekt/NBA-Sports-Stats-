package use_case.menu;

import interface_adapter.menu.MenuViewPresenter;

/**
 * The Interactor for the MenuView.
 */
public class MenuViewInteractor {

    private final MenuViewPresenter menuViewPresenter;

    public MenuViewInteractor(MenuViewPresenter menuViewPresenter) {
        this.menuViewPresenter = menuViewPresenter;
    }

    /**
     * Switches to TeamCompare view.
     */
    public void switchToTeamCompareView() {
        menuViewPresenter.switchToTeamCompareView();
    }

    /**
     * Switches to Player view.
     */
    public void switchToPlayerView() {
        menuViewPresenter.switchToPlayerView();
    }

    /**
     * Switches to Team view.
     */
    public void switchToTeamView() {
        menuViewPresenter.switchToTeamView();
    }
}
