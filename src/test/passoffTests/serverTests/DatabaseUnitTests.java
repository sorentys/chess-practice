package passoffTests.serverTests;

import chess.ChessGame;
import chess.ChessGameImplementation;
import dataAccess.AuthDAO;
import dataAccess.Database;
import models.AuthToken;
import models.Game;
import models.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class DatabaseUnitTests {
    private static Database database = new Database();



    @Test
    public void insertAuthPositiveTest() throws Exception {
        //put a good authToken in the mysql database
        String good_auth_token = "good auth token";
        String good_user_name = "good_user_name";
        AuthToken auth_token = new AuthToken(good_auth_token, good_user_name);
        Connection conn = database.getConnection();
        database.insertAuth(conn, auth_token);
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM auths WHERE authToken = ?")) {
            preparedStatement.setString(1, auth_token.getAuthToken());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_auth_token = rs.getString("authToken");
                String database_username = rs.getString("username");
                // make sure auth token got inserted into the database correctly
                assertEquals(good_auth_token, database_auth_token);
                assertEquals(good_user_name, database_username);
            }
        }
        try (var secondPreparedStatement = conn.prepareStatement("DELETE FROM auths WHERE authToken = ?")) {
            secondPreparedStatement.setString(1, auth_token.getAuthToken());

            secondPreparedStatement.executeUpdate();
        }
        database.removeAuth(conn, auth_token);
    }

    @Test
    public void insertAuthNegativeTest() throws Exception {
        //try adding same authToken twice
        String bad_auth_token = "bad auth token";
        String bad_user_name = "bad_user_name";
        AuthToken auth_token = new AuthToken(bad_auth_token, bad_user_name);
        Connection conn = database.getConnection();
        try {
            database.insertAuth(conn, auth_token);
            database.insertAuth(conn, auth_token);
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
        database.removeAuth(conn, auth_token);
    }

    @Test
    public void findAuthPositiveTest() throws Exception {
        //find a good AuthToken in the mysql database
        String good_auth_token = "good auth token";
        String good_password = "good password";
        AuthToken auth_token = new AuthToken(good_auth_token, good_password);
        Connection conn = database.getConnection();
        try {
            database.insertAuth(conn, auth_token);
            AuthToken database_auth_token = database.findAuth(conn, auth_token);
            assertEquals(database_auth_token.getAuthToken(), auth_token.getAuthToken());
            assertEquals(database_auth_token.getUsername(), auth_token.getUsername());
            database.removeAuth(conn, auth_token);
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
    }

    @Test
    public void findAuthNegativeTest() throws Exception {
        //try to find an AuthToken that once existed but no longer exists
        String good_auth_token = "good user token";
        String good_password = "good password";
        AuthToken auth_token = new AuthToken(good_auth_token, good_password);
        Connection conn = database.getConnection();
        try {
            database.insertAuth(conn, auth_token);
            database.removeAuth(conn, auth_token);
            database.findAuth(conn, auth_token);
        } catch (IllegalArgumentException err) {
            assertNotNull(err.getMessage());
        }
    }

    @Test
    public void removeAuthPositiveTest() throws Exception {
        //remove a good AuthToken from the mysql database
        String good_auth_token = "good auth token";
        String good_password = "good password";
        AuthToken auth_token = new AuthToken(good_auth_token, good_password);
        Connection conn = database.getConnection();
        database.insertAuth(conn, auth_token);
        database.removeAuth(conn, auth_token);
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM auths WHERE authToken = ?")){
            preparedStatement.setString(1, auth_token.getAuthToken());
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
    }

    @Test
    public void removeAuthNegativeTest() throws Exception {
        //try to remove an AuthToken that doesn't exist from the mysql database
        String good_auth_token = "good auth token";
        String good_password = "good password";
        AuthToken auth_token = new AuthToken(good_auth_token, good_password);
        Connection conn = database.getConnection();
        try {
            database.removeAuth(conn, auth_token);
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
    }

    @Test
    public void insertUserPositiveTest() throws Exception {
        //put a good User in the mysql database
        String good_user_name = "good_user_name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_user_name, good_password, good_email);
        Connection conn = database.getConnection();
        database.insertUser(conn, user);
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, user.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_user_name = rs.getString("username");
                String database_password = rs.getString("password");
                String database_email = rs.getString("email");
                // make sure user got inserted into the database correctly
                assertEquals(good_user_name, database_user_name);
                assertEquals(good_password, database_password);
                assertEquals(good_email, database_email);
            }
        }
        try (var secondPreparedStatement = conn.prepareStatement("DELETE FROM users WHERE username = ?")) {
            secondPreparedStatement.setString(1, user.getUsername());

            secondPreparedStatement.executeUpdate();
        }
        database.removeUser(conn, user.getUsername());
    }

    @Test
    public void insertUserNegativeTest() throws Exception{
        //try to put a User in with the same user_name as another but different password
        String good_user_name = "good_user_name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_user_name, good_password, good_email);
        String bad_password = "bad_password";
        User bad_user = new User(good_user_name, bad_password, good_email);
        Connection conn = database.getConnection();
        try {
            database.insertUser(conn, user);
            database.insertUser(conn, bad_user);
        } catch (IllegalArgumentException err) {
            assertNotNull(err.getMessage());
        }
        database.removeUser(conn, user.getUsername());
    }
    @Test
    public void findUserPositiveTest() throws Exception {
        //find a user in the database
        String good_username = "good user name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_username, good_password, good_email);
        Connection conn = database.getConnection();
        try {
            database.insertUser(conn, user);
            User database_user = database.findUser(conn, user);
            assertEquals(database_user.getUsername(), good_username);
            assertEquals(database_user.getPassword(), good_password);
            assertEquals(database_user.getEmail(), good_email);
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
        database.removeUser(conn, user.getUsername());
    }

    @Test
    public void findUserNegativeTest() throws Exception {
        //try to find a user in the database that has already been removed
        String good_username = "good user name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_username, good_password, good_email);
        Connection conn = database.getConnection();
        try {
            database.insertUser(conn, user);
            database.removeUser(conn, user.getUsername());
            User database_user = database.findUser(conn, user);
        } catch (IllegalArgumentException err) {
            assertNotNull(err.getMessage());
        }
    }

    @Test
    public void removeUserPositiveTest() throws Exception {
        //remove a good user from the database
        String good_username = "good user name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_username, good_password, good_email);
        Connection conn = database.getConnection();
        try {
            database.insertUser(conn, user);
            String user_removed = database.removeUser(conn, user.getUsername());
            assertEquals(user_removed, good_username);
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
    }

    @Test
    public void removeUserNegativeTest() throws Exception {
        //try to remove a user by a wrong username
        String good_username = "good user name";
        String good_password = "good_password";
        String good_email = "good@email.com";
        User user = new User(good_username, good_password, good_email);
        Connection conn = database.getConnection();
        try {
            database.insertUser(conn, user);
            String user_removed = database.removeUser(conn, "bad user name unique");
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
        database.removeUser(conn, user.getUsername());
    }

    @Test
    public void insertGamePositiveTest() throws Exception {
        //put a good Game in the mysql database
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");
        Connection conn = database.getConnection();
        database.insertGame(conn, chess_game);
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM games WHERE gameName = ?")) {
            preparedStatement.setString(1, chess_game.getGameName());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_game_name = rs.getString("gameName");
                String database_black_username = rs.getString("blackUsername");
                String database_white_username = rs.getString("whiteUsername");
                int database_game_id = rs.getInt("gameID");
                // make sure game got inserted into the database correctly
                assertEquals(game_name, database_game_name);
                assertEquals("black piece player", database_black_username);
                assertEquals("white piece player", database_white_username);
                assertEquals(4000, database_game_id);
            }
        }
        try (var secondPreparedStatement = conn.prepareStatement("DELETE FROM games WHERE gameName = ?")) {
            secondPreparedStatement.setString(1, chess_game.getGameName());

            secondPreparedStatement.executeUpdate();
        }
        database.removeGame(conn, chess_game);
    }

    @Test
    public void insertGameNegativeTest() throws Exception {
        //try to insert Game in the mysql database with same gameID as another Game
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");

        String bad_game_name = "bad game name";
        Game bad_chess_game = new Game(bad_game_name);
        bad_chess_game.setGameID(4000);

        Connection conn = database.getConnection();
        try {
            database.insertGame(conn, chess_game);
            database.insertGame(conn, bad_chess_game);
        } catch (SQLException err) {
            assertNotNull(err);
        }
        database.removeGame(conn, chess_game);
    }

    @Test
    public void findGamePositiveTest() throws Exception {
        //find a good Game in the database
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");

        Connection conn = database.getConnection();
        try {
            database.insertGame(conn, chess_game);
            Game database_game = database.findGame(conn, chess_game.getGameID());
            assertEquals(database_game.getGameID(), chess_game.getGameID());
            assertEquals(database_game.getGameName(), chess_game.getGameName());
            assertEquals(database_game.getWhiteUsername(), chess_game.getWhiteUsername());
            assertEquals(database_game.getBlackUsername(), chess_game.getBlackUsername());
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
        database.removeGame(conn, chess_game);
    }

    @Test
    public void findGameNegativeTest() throws Exception {
        //try to find a game that has been deleted from the database
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");

        Connection conn = database.getConnection();
        try {
            database.insertGame(conn, chess_game);
            database.removeGame(conn, chess_game);
            Game database_game = database.findGame(conn, chess_game.getGameID());
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
        database.removeGame(conn, chess_game);
    }

    @Test
    public void removeGamePositiveTest() throws Exception {
        //remove a good Game from the database
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");

        Connection conn = database.getConnection();
        try {
            database.insertGame(conn, chess_game);
            database.removeGame(conn, chess_game);
        } catch (SQLException err) {
            assertNull(err.getMessage());
        }
    }

    @Test
    public void removeGameNegativeTest() throws Exception {
        //remove a Game from the database that has already been remeoved
        String game_name = "good game name";
        Game chess_game = new Game(game_name);
        chess_game.setGame(new ChessGameImplementation());
        chess_game.setGameID(4000);
        chess_game.setBlackUsername("black piece player");
        chess_game.setWhiteUsername("white piece player");

        Connection conn = database.getConnection();
        try {
            database.insertGame(conn, chess_game);
            database.removeGame(conn, chess_game);
            database.removeGame(conn, chess_game);
        } catch (SQLException err) {
            assertNotNull(err.getMessage());
        }
    }

    @Test
    public void clearAllPositiveTest() throws Exception {
        //clear all database
        Connection conn = database.getConnection();
        database.clearAllAuths(conn);
        database.clearAllUsers(conn);
        database.clearAllGames(conn);
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM auths, users, games")) {
            ResultSet rs = preparedStatement.executeQuery();
            assertFalse(rs.next());
        }
    }
}

