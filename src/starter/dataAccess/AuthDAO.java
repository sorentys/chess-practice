package dataAccess;

import models.AuthToken;
import models.Game;
import models.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

/**
 * DAO responsible for handling authentication of the chess game
 */
public class AuthDAO {
    /**
     * data structure for storing authTokens. "authToken" : authToken
     */
    private static Map<String, AuthToken> auth_tokens = new HashMap<String, AuthToken>();
    private static Database database = new Database();
    /**
     * inserts a new token into the database
     * @param auth_token token to insert to database
     */
    public void insertAuth(AuthToken auth_token) throws Exception {
        String new_auth_token = UUID.randomUUID().toString();
        auth_token.setAuthToken(new_auth_token);
        // auth_tokens.put(auth_token.getAuthToken(), auth_token);
        Connection conn = database.getConnection();
        database.insertAuth(conn, auth_token);
        database.closeConnection(conn);
    }

    /**
     * finds a token in the database by username
     * @param auth_token_to_find token to find
     */
    public void findAuth(AuthToken auth_token_to_find) throws Exception {
        Connection conn = database.getConnection();
        database.findAuth(conn, auth_token_to_find);
        database.closeConnection(conn);
    }

    /**
     * removes an AuthToken from the database
     * @param auth_token the AuthToken to remove
     */
    public void removeAuth(AuthToken auth_token) throws Exception{
        Connection conn = database.getConnection();
        database.removeAuth(conn, auth_token);
        database.closeConnection(conn);
    }

    /**
     * Clears all authentication tokens from the database
     */
    public void clearAllAuths() throws Exception {
        Connection conn = database.getConnection();
        database.clearAllAuths(conn);
        database.closeConnection(conn);
    }

    /**
     * finds the username of a user based on their AuthToken string
     * @param auth_token_string the AuthToken string to use to find the user
     */
    public String findUser(String auth_token_string) throws Exception {
        if (auth_token_string == null) {
            throw new IllegalArgumentException("Error unauthorized no username");
        }
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM auths WHERE authToken = ?")){
            preparedStatement.setString(1, auth_token_string);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String database_user_name = rs.getString("username");
                if (!database_user_name.isEmpty()) {
                    return database_user_name;
                }
            }
        }
        return null;
    }

    /**
     * lists all AuthTokens in the database
     */
    public ArrayList<AuthToken> listAllAuths() throws Exception{
        ArrayList<AuthToken> auth_list = new ArrayList<AuthToken>();
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM auths")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String auth_token_string = rs.getString("authToken");
                String username = rs.getString("username");
                AuthToken auth_token = new AuthToken(auth_token_string, username);
                auth_list.add(auth_token);
            }
        }
        return auth_list;
    }
}
