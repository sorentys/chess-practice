package responses;

/**
 * Response to logging into a server
 */
public class LoginResponse {
    /**
     * response message to request
     */
    private String message;

    /**
     * authentication token
     */
    private String authToken;

    /**
     * chess player username
     */
    private String username;

    public LoginResponse() {}

    public String getMessage() {
        return message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }
}
