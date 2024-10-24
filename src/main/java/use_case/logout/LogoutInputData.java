package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String user;

    public LogoutInputData(String username) {
        // TODO: save the current username in an instance variable and add a getter.
        this.user = username;
    }

    public String getUser() {
        return user;
    }
}
