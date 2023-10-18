package responses;

/**
 * Response to logging into a server
 */
public class LoginResponse {
    private String message;
    private String authToken;
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
