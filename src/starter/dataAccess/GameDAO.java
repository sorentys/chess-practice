package dataAccess;

import chess.ChessGameImplementation;
import models.Game;

import java.util.ArrayList;

/**
 * DAO for chess game services
 */
public class GameDAO {
    /**
     * inserts a new game into the database
     * @param chess_game the chess game to insert
     */
    public void insertGame(Game chess_game) {}

    /**
     * finds a game in the database by gameID
     * @param chess_game_id id of the chess game
     * @return a chess game
     */
    public Game findGame(int chess_game_id) {return null;}

    /**
     * Returns all the games in the database
     * @return a list of chess games
     */
    public ArrayList<Game> findAllGames() {return null;}

    /**
     * Method to claim a spot in a chess game
     * @param player_username the player's username
     *
     */
    public void claimSpotInGame(String player_username) {}

    /**
     * Updates a chess game in the database
     * @param chess_game_id the id of the chess game
     * @return a chess game string of the updated game
     */
    public String updateGame(int chess_game_id) {return null;}

    /**
     * Removes a chess game from the database
     * @param chess_game_id the id of the chess game
     *
     */
    public void removeGame(int chess_game_id) {}

    /**
     * Clears all data from the database
     */
    public void clearDatabase() {}
}
