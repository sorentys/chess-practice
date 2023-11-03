package requests;

import models.Game;

/**
 * Request to make a game
 */
public class JoinGameRequest {
    /**
     * chess player's color (black or white)
     */
    private String playerColor;

    /**
     * chess game identification number
     */
    private Integer gameID;

    private String authToken;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public Integer getGameID() {
        return gameID;
    }

    public JoinGameRequest(String playerColor, Integer gameID, String authToken) {
        this.playerColor = playerColor;
        this.gameID = gameID;
        this.authToken = authToken;
    }
}
