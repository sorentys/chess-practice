package dataAccess;

import chess.ChessGame;
import chess.ChessGameImplementation;
import chess.InvalidMoveException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import models.AuthToken;
import models.Game;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Responsible for creating connections to the database. Connections should be closed after use, either by calling
 * {@link #closeConnection(Connection)} on the Database instance or directly on the connection.
 */
public class Database {

    // FIXME: Change these fields, if necessary, to match your database configuration
    public static final String DB_NAME = "<enter db name>";
    private static final String DB_USERNAME = "<enter username>";
    private static final String DB_PASSWORD = "<enter password>";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    /**
     * Gets a connection to the database.
     *
     * @return Connection the connection.
     * @throws DataAccessException if a data access error occurs.git
     */
    public Connection getConnection() throws DataAccessException {
        try {
            return DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Closes the specified connection.
     *
     * @param connection the connection to be closed.
     * @throws DataAccessException if a data access error occurs while closing the connection.
     */
    public void closeConnection(Connection connection) throws DataAccessException {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }
        }
    }

    /**
     * Inserts an authToken into the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param auth_token the authToken to add to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void insertAuth(Connection conn, AuthToken auth_token) throws Exception {
        try (var preparedStatement = conn.prepareStatement("INSERT INTO auths (authToken, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, auth_token.getAuthToken());
            preparedStatement.setString(2, auth_token.getUsername());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Finds an authToken in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param auth_token the authToken to find to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public AuthToken findAuth(Connection conn, AuthToken auth_token) throws Exception {
        try (var preparedStatement =  conn.prepareStatement("SELECT * FROM auths Where authToken = ?")) {
            preparedStatement.setString(1, auth_token.getAuthToken());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_auth_token = rs.getString("authToken");
                String database_user_name = rs.getString("username");

                if (!database_auth_token.equals(auth_token.getAuthToken())) {
                    throw new IllegalArgumentException("Error: unauthorized");
                }
                return new AuthToken(database_auth_token, database_user_name);
            } else {
                throw new IllegalArgumentException("Error: unauthorized bad auth token");
            }
        }
    }

    /**
     * Removes a authToken from the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param auth_token the authToken to remove to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void removeAuth(Connection conn, AuthToken auth_token) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM auths WHERE authToken = ?")) {
            preparedStatement.setString(1, auth_token.getAuthToken());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Clears ALL authToken in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void clearAllAuths(Connection conn) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM auths")) {
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Inserts a User into the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param user the user to add to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void insertUser(Connection conn, User user) throws Exception {
        try (var preparedStatement = conn.prepareStatement("INSERT INTO users (username, password, email) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (Exception err) {
            throw new IllegalArgumentException("Error: already taken" + err.getMessage());
        }
    }

    /**
     * Finds a User in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param user the User to find to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public User findUser(Connection conn, User user) throws Exception {
        try (var preparedStatement =  conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, user.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_user_name = rs.getString("username");
                String database_password = rs.getString("password");
                String database_email = rs.getString("email");

                if (!database_user_name.equals(user.getUsername()) || !database_password.equals(user.getPassword())) {
                    throw new IllegalArgumentException("Error: unauthorized");
                }
                return new User(database_user_name, database_password, database_email);
            } else {
                throw new IllegalArgumentException("Error: unauthorized user or password not found in database");
            }
        }
    }

    /**
     * Removes a User from the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param user_name the username of the User to remove to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public String removeUser(Connection conn, String user_name) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM users WHERE username = ?")) {
            preparedStatement.setString(1, user_name);

            preparedStatement.executeUpdate();
            return user_name;
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
    }

    /**
     * Clears ALL Users in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void clearAllUsers(Connection conn) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM users")) {
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Inserts a Game into the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param game the Game to add to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void insertGame(Connection conn, Game game) throws Exception {
        try (var preparedStatement = conn.prepareStatement("INSERT INTO games (gameID, game, gameName, whiteUsername, blackUsername) VALUES(?, ?, ?, ?, ?)")) {
            String game_json = new Gson().toJson(game.getGame());

            preparedStatement.setInt(1, game.getGameID());
            preparedStatement.setString(2, game_json);
            preparedStatement.setString(3, game.getGameName());
            preparedStatement.setString(4, game.getWhiteUsername());
            preparedStatement.setString(5, game.getBlackUsername());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Finds a Game in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param chess_game_id the gameID of the Game to find to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public Game findGame(Connection conn, int chess_game_id) throws Exception {
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM games WHERE gameID = ?")) {
            preparedStatement.setInt(1, chess_game_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int game_id = rs.getInt("gameID");
                String game_json = rs.getString("game");
                ChessGame actual_chess_game = new Gson().fromJson(game_json, ChessGameImplementation.class);
                String game_name = rs.getString("gameName");
                String game_white_username = rs.getString("whiteUsername");
                String game_black_username = rs.getString("blackUsername");
                Game game_to_return = new Game(game_name);
                game_to_return.setGameID(game_id);
                game_to_return.setBlackUsername(game_black_username);
                game_to_return.setWhiteUsername(game_white_username);
                game_to_return.setGame(actual_chess_game);
                return game_to_return;
            }
        }
        return null;
    }

    /**
     * Removes a Game from the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @param game the Game to remove to the database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void removeGame(Connection conn, Game game) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM games WHERE gameID = ?")) {
            preparedStatement.setInt(1, game.getGameID());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Clears ALL Games in the mysql database.
     *
     * @param conn the connection to the mysql database.
     * @throws SQLException if there are errors between the data and the mysql database.
     */
    public void clearAllGames(Connection conn) throws Exception {
        try (var preparedStatement = conn.prepareStatement("DELETE FROM games")) {
            preparedStatement.executeUpdate();
        }
    }
}
