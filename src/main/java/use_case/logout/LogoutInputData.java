package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    private final String currentUsername;

    public LogoutInputData(String username) {
        this.currentUsername = username;
        // TODO: save the current username in an instance variable and add a getter - Completed by Benedict
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
