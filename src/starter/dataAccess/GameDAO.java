package dataAccess;

import chess.ChessGameImplementation;
import models.Game;
import models.User;

import java.awt.List;
import java.util.*;

/**
 * DAO for chess game services
 */
public class GameDAO {
    private static int unique_id = 0;
    /**
     * data structure for storing authTokens. "gameName" : Game
     */
    private static Map<String, Game> games = new HashMap<String, Game>();
    /**
     * inserts a new game into the database
     * @param chess_game the chess game to insert
     */
    public void insertGame(Game chess_game) {
        if (games.containsKey(chess_game.getGameName())) {
            throw new IllegalArgumentException("Error: already taken");
        }
        else {
            unique_id++;
            chess_game.setGameID(unique_id);
            games.put(chess_game.getGameName(), chess_game);
        }
    }

    /**
     * finds a game in the database by gameID
     * @param chess_game_id id of the chess game
     * @return a chess game
     */
    public Game findGame(int chess_game_id) throws IllegalArgumentException {
        for (Game game : games.values()) {
            int games_game_id = game.getGameID();
            if (games_game_id == chess_game_id) {
                return game;
            }
        }
        throw new IllegalArgumentException("Error: bad request no game with given chess_game_id");
    }

    /**
     * Returns all the games in the database
     * @return a list of chess games
     */
    public ArrayList<Game> listAllGames() {
        ArrayList<Game> games_list = new ArrayList<Game>();
        for (Game one_game : games.values()) {;
            games_list.add(one_game);
        }
        return games_list;
    }

    /**
     * Method to claim a spot in a chess game
     * @param player_color the player's desired color
     * @param chess_game the chess game to change
     *
     */
    public void claimSpotInGame(String player_color, String user_to_join, Game chess_game) throws IllegalArgumentException {
        if (chess_game == null) {
            throw new IllegalArgumentException("Error: bad request");
        }
        else if (chess_game.getBlackUsername() != null && player_color.equals("BLACK") ||
                chess_game.getWhiteUsername() != null && player_color.equals("WHITE")) {
            throw new IllegalArgumentException("Error: already taken");
        }
        if (player_color.equals("WHITE")) {
            chess_game.setWhiteUsername(user_to_join);
        }
        if (player_color.equals("BLACK")) {
            chess_game.setBlackUsername(user_to_join);
        }
    }

    /**
     * Removes a chess game from the database
     * @param chess_game the chess game
     *
     */
    public void removeGame(Game chess_game) {
        if (!games.containsKey(chess_game.getGameName())) {
            throw new IllegalArgumentException("Error: unauthorized");
        }
        else {
            games.remove(chess_game.getGameName());
        }
    }

    /**
     * Clears all games from the database
     */
    public void clearAllGames() {
        games.clear();
    }
}
