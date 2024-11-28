package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import view.MenuView;

public class TeamPresenter {

    private final MenuView menuView;
    private final ViewManagerModel viewManagerModel;

    public TeamPresenter(MenuView menuView, ViewManagerModel viewManagerModel) {
        this.menuView = menuView;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToMenuView() {
        viewManagerModel.setState(menuView.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
