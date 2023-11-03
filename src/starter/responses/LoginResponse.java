package responses;

/**
 * Response to logging into a server
 */
public class LoginResponse {
    /**
     * chess player username
     */
    private String username;

    /**
     * response message to request
     */
    private String message;

    /**
     * authentication token
     */
    private String authToken;

    public String getMessage() {
        return message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public LoginResponse(String username, String authToken, String message) {
        this.username = username;
        this.authToken = authToken;
        this.message = message;
    }
}
