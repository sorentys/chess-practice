package requests;

/**
 * Request to create a chess game
 */
public class CreateGameRequest {
    /**
     * authentication token
     */
    private String auth_token;

    /**
     * chess game name
     */
    private String game_name;

    public CreateGameRequest() {}
}
