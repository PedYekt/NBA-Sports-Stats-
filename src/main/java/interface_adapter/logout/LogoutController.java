package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private final LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        // 1. Instantiate the LogoutInputData, which should contain the username.
        final LogoutInputData logoutInputData = new LogoutInputData(username);

        // 2. Tell the Interactor to execute.
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
