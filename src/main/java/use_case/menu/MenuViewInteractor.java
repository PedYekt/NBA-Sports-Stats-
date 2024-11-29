package use_case.menu;

import interface_adapter.menu.MenuViewPresenter;

public class MenuViewInteractor {

    private final MenuViewPresenter menuViewPresenter;

    public MenuViewInteractor(MenuViewPresenter menuViewPresenter) {
        this.menuViewPresenter = menuViewPresenter;
    }

    public void switchToTeamCompareView() {
        menuViewPresenter.switchToTeamCompareView();
    }

    public void switchToPlayerView() {
        menuViewPresenter.switchToPlayerView();
    }

    public void switchToTeamView() {
        menuViewPresenter.switchToTeamView();
    }
}
