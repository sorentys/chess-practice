package models;

import chess.ChessGame;

/**
 * Chess game instance for dealing with a chess game
 */
public class Game {
    /**
     * game identification number
     */
    private int game_id;

    /**
     * username of white piece color player
     */
    private String white_username;

    /**
     * username of black piece color player
     */
    private String black_username;

    /**
     * chess game name
     */
    private String game_name;

    /**
     * the actual chess game
     */
    private ChessGame game;

    /**
     * the black and white type colors to assign
     */
    public enum PlayerColor {
        WHITE,
        BLACK
    }
    public Game() {}

}
