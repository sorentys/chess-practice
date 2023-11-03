package models;

/**
 * AuthToken instance for authenticating a user
 */
public class AuthToken {
    /**
     * authentication token
     */
    private String authToken;
    /**
     * username to authenticate
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String token_to_set) {
        this.authToken = token_to_set;
    }

    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }
}
