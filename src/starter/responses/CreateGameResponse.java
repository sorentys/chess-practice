package responses;

/**
 * Response to creating a chess game
 */
public class CreateGameResponse {
    /**
     * chess game identification number
     */
    private Integer gameID;

    /**
     * response message to request
     */
    private String message;

    public Integer getGameID() {
        return gameID;
    }

    public String getMessage() {
        return message;
    }

    /**
     * CreateGameResponse constructor
     * @param gameID the identification number of a chess game
     * @param message the response message
     */
    public CreateGameResponse(Integer gameID, String message) {
        this.gameID = gameID;
        this.message = message;
    }
}
