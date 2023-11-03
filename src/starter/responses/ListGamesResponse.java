package responses;

import models.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Response to listing all games in a database
 */
public class ListGamesResponse {
    /**
     * list of chess games in the database
     */
    private ArrayList<Game> games;

    /**
     * response message to request
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ListGamesResponse(ArrayList<Game> games, String message) {
        this.games = games;
        this.message = message;
    }
}
