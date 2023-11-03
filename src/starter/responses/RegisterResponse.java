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

    public RegisterResponse(String username, String authToken, String message) {
        this.username = username;
        this.authToken = authToken;
        this.message = message;
    }
}
