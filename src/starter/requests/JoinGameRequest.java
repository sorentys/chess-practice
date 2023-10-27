package requests;

import models.Game;

/**
 * Request to make a game
 */
public class JoinGameRequest {
    /**
     * chess player's ENUM color (black or white)
     */
    private Game.PlayerColor player_color;

    /**
     * chess game identification number
     */
    private int game_id;

    public JoinGameRequest() {}
}
