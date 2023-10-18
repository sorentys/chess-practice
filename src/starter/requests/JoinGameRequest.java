package requests;

import models.Game;

/**
 * Request to make a game
 */
public class JoinGameRequest {

    private Game.PlayerColor player_color;
    private int game_id;

    public JoinGameRequest() {}
}
