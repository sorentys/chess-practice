package responses;

/**
 * Response to registering a user or game
 */
public class RegisterResponse {
    /**
     * chess player username
     */
    private String username;

    /**
     * authentication token
     */
    private String authToken;

    /**
     * response message to requests
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }

    /**
     * RegisterResponse constructor
     * @param username a username of a user to login
     * @param authToken the AuthToken string to authenticate the user
     * @param message the response message
     */
    public RegisterResponse(String username, String authToken, String message) {
        this.username = username;
        this.authToken = authToken;
        this.message = message;
    }
}
