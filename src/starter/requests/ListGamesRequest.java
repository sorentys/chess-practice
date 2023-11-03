package requests;

/**
 * Request to list all games in a database
 */
public class ListGamesRequest {
    /**
     * authentication token
     */
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public ListGamesRequest(String authToken) {
        this.authToken = authToken;
    }
}
