package interface_adapter.menu;

import use_case.menu.MenuViewInteractor;

/**
 * The controller for the MenuView.
 */
public class MenuViewController {

    private final MenuViewInteractor menuViewInteractor;

    public MenuViewController(MenuViewInteractor menuViewInteractor) {
        this.menuViewInteractor = menuViewInteractor;
    }

    /**
     * Switches to TeamCompare view.
     */
    public void switchToTeamCompareView() {
        menuViewInteractor.switchToTeamCompareView();
    }

    /**
     * Switches to Player view.
     */
    public void switchToPlayerView() {
        menuViewInteractor.switchToPlayerView();
    }

    /**
     * Switches to Team view.
     */
    public void switchToTeamView() {
        menuViewInteractor.switchToTeamView();
    }
}
