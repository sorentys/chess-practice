package models;

import chess.ChessGame;

public class Game {

    private int game_id;
    private String white_username;
    private String black_username;
    private String game_name;
    private ChessGame game;

    public enum PlayerColor {
        WHITE,
        BLACK
    }
    public Game() {}

}
