package interface_adapter.menu;

import use_case.menu.MenuViewInteractor;

public class MenuViewController {

    private final MenuViewInteractor menuViewInteractor;

    public MenuViewController(MenuViewInteractor menuViewInteractor) {
        this.menuViewInteractor = menuViewInteractor;
    }

    public void switchToTeamCompareView() {
        menuViewInteractor.switchToTeamCompareView();
    }

    public void switchToTeamView() {
        menuViewInteractor.switchToTeamView();
    }
}
