package requests;

/**
 * Request to log out of server
 */
public class LogoutRequest {
    /**
     * authentication token
     */
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    /**
     * LogoutRequest constructor
     * @param authToken AuthToken string to authenticate logging out
     */
    public LogoutRequest(String authToken) {
        this.authToken = authToken;
    }

}
