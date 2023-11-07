package requests;

/**
 * Request to create a chess game
 */
public class CreateGameRequest {
    /**
     * authentication token
     */
    private String authToken;

    /**
     * chess game name
     */
    private String gameName;

    public String getAuthToken() {
        return authToken;
    }

    public String getGameName() {
        return gameName;
    }

    public void setAuthToken(String auth_token_to_set) {
        this.authToken = auth_token_to_set;
    }

    /**
     * CreateGameRequest constructor
     * @param authToken AuthToken string to authenticate game creation
     * @param gameName what to name the chess game
     */
    public CreateGameRequest(String authToken, String gameName) {
        this.authToken = authToken;
        this.gameName = gameName;
    }
}
