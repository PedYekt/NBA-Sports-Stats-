package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // TODO: save the DAO and Presenter in the instance variables.
        // Which parameter is the DAO and which is the presenter?
        this.logoutPresenter = logoutOutputBoundary;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // TODO: implement the logic of the Logout Use Case (depends on the LogoutInputData.java TODO)
        // * get the username out of the input data,
        String username = logoutInputData.username;
        // * set the username to null in the DAO
        userDataAccessObject = null;
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        LogoutOutputData logoutOutputData = new LogoutOutputData(username, true);
        // * tell the presenter to prepare a success view.
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}

