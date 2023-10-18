package models;

import chess.ChessGame;

/**
 * Chess game instance for dealing with a chess game
 */
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
