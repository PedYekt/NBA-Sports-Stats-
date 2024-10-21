package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // Which parameter is the DAO and which is the presenter?
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;

    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // depends on inputdata?
        // * get the username out of the input data,
        // * set the username to null in the DAO
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        // * tell the presenter to prepare a success view.
        final String username = logoutInputData.getName();
        userDataAccessObject.setCurrentUsername(null);
        final boolean ffalse = false;
        final LogoutOutputData outData = new LogoutOutputData(username, ffalse);
        logoutPresenter.prepareSuccessView(outData);
    }
}

