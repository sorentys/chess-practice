package responses;

/**
 * Response to joining a chess game
 */
public class JoinGameResponse {
    /**
     * response message to request
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public JoinGameResponse(String message) {
        this.message = message;
    }
}
