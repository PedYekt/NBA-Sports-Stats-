package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {

    private final LogoutUserDataAccessInterface userDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // TODO: save the DAO and Presenter in the instance variables - Completed by Benedict
        // Which parameter is the DAO and which is the presenter?
        this.logoutPresenter = logoutOutputBoundary;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // TODO: implement the logic of the Logout Use Case - Completed by Benedict
        // * get the username out of the input data,
        final String username = logoutInputData.getCurrentUsername();
        // * set the username to null in the DAO
        this.userDataAccessObject.setCurrentUsername(username);
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        final LogoutOutputData logoutOutputData = new LogoutOutputData(username, false);
        // * tell the presenter to prepare a success view.
        this.logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}

