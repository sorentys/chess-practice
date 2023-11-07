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

    /**
     * ListGamesRequest Constructor
     * @param authToken AuthToken string to set for the request
     */
    public ListGamesRequest(String authToken) {
        this.authToken = authToken;
    }
}
