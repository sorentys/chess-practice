package responses;

import models.Game;

import java.util.ArrayList;

public class ListGamesResponse {
    private ArrayList<Game> game_list;
    private int game_id;
    private String white_username;
    private String black_username;
    private String game_name;
    private String message;

    public ListGamesResponse() {}
}
