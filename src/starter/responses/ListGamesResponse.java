package responses;

import models.Game;

import java.util.ArrayList;

/**
 * Response to listing all games in a database
 */
public class ListGamesResponse {
    /**
     * list of chess games in the database
     */
    private ArrayList<Game> game_list;

    /**
     * chess game identification number
     */
    private int game_id;

    /**
     * player username of white piece color
     */
    private String white_username;

    /**
     * player username of black piece color
     */
    private String black_username;

    /**
     * chess game name
     */
    private String game_name;

    /**
     * response message to request
     */
    private String message;

    public ListGamesResponse() {}
}
