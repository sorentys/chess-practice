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

    public LogoutRequest(String authToken) {
        this.authToken = authToken;
    }

}
