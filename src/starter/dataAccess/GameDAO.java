package dataAccess;

import chess.ChessGame;
import chess.ChessGameImplementation;
import models.AuthToken;
import models.Game;
import models.User;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

/**
 * DAO for chess game services
 */
public class GameDAO {
    private static Database database = new Database();
    private static int unique_id = 0;
    /**
     * data structure for storing authTokens. "gameName" : Game
     */
    private static Map<String, Game> games = new HashMap<String, Game>();
    /**
     * inserts a new game into the database
     * @param chess_game the chess game to insert
     */
    public void insertGame(Game chess_game) throws Exception {
        if (games.containsKey(chess_game.getGameName())) {
            throw new IllegalArgumentException("Error: already taken");
        }
        else {
            unique_id++;
            chess_game.setGameID(unique_id);
            chess_game.setGame(new ChessGameImplementation());
            Connection conn = database.getConnection();
            database.insertGame(conn, chess_game);
            database.closeConnection(conn);

        }
    }

    /**
     * finds a game in the database by gameID
     * @param chess_game_id id of the chess game
     * @return a chess game
     */
    public Game findGame(int chess_game_id) throws Exception {
        Connection conn = database.getConnection();
        Game game = database.findGame(conn, chess_game_id);
        if (game != null) {
            return game;
        }
        throw new IllegalArgumentException("Error: unauthorized bad request no game with given chess_game_id");
    }

    /**
     * Returns all the games in the database
     * @return a list of chess games
     */
    public ArrayList<Game> listAllGames() throws Exception {
        ArrayList<Game> games_list = new ArrayList<Game>();
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM games")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int database_game_id = rs.getInt("gameID");
                Game game = database.findGame(conn, database_game_id);
                games_list.add(game);
            }
        }
        return games_list;
    }


    /**
     * Method to claim a spot in a chess game
     * @param player_color the player's desired color
     * @param chess_game the chess game to change
     *
     */
    public void claimSpotInGame(String player_color, String user_to_join, Game chess_game) throws Exception {
        if (chess_game == null) {
            throw new IllegalArgumentException("Error: bad request");
        }
        Connection conn = database.getConnection();
        Game game_to_update = database.findGame(conn, chess_game.getGameID());
        if (game_to_update == null) {
            throw new IllegalArgumentException("Error: unauthorized game not in database");
        } else if (game_to_update.getBlackUsername() != null && player_color.equals("BLACK") ||
                game_to_update.getWhiteUsername() != null && player_color.equals("WHITE")) {
            throw new IllegalArgumentException("Error: already taken");
        }
        String sql_string = "";
        if (player_color.equals("WHITE")) {
            sql_string = "UPDATE games SET whiteUsername = ? Where gameID = ?";
        }
        if (player_color.equals("BLACK")) {
            sql_string = "UPDATE games SET blackUsername = ? Where gameID = ?";
        }
        try (var preparedStatement = conn.prepareStatement(sql_string)) {
            preparedStatement.setString(1, user_to_join);
            preparedStatement.setInt(2, game_to_update.getGameID());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Removes a chess game from the database
     * @param chess_game the chess game
     *
     */
    public void removeGame(Game chess_game) throws Exception {
        Connection conn = database.getConnection();
        database.removeGame(conn, chess_game);
        database.closeConnection(conn);
    }

    /**
     * Clears all games from the database
     */
    public void clearAllGames() throws Exception {
        Connection conn = database.getConnection();
        database.clearAllGames(conn);
        database.closeConnection(conn);
    }
}
