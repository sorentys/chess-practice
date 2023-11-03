package models;

import chess.ChessGame;

/**
 * Chess game instance for dealing with a chess game
 */
public class Game {
    /**
     * game identification number
     */
    private int gameID;

    /**
     * username of white piece color player
     */
    private String whiteUsername;

    /**
     * username of black piece color player
     */
    private String blackUsername;

    /**
     * chess game name
     */
    private String gameName;

    /**
     * the actual chess game
     */
    private ChessGame game;

    private String playerColor;

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public Game(String gameName) {
        this.gameName = gameName;
    }

}
