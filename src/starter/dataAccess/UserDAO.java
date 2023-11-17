package dataAccess;

import models.AuthToken;
import models.Game;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO for User services
 */
public class UserDAO {
    private static Map<String, User> users = new HashMap<String, User>();
    private static Database database = new Database();
    /**
     * Adds a user to the database
     * @param user_to_insert user to add
     */
    public void insertUser(User user_to_insert) throws Exception {
        if (users.containsKey(user_to_insert.getUsername())) {
            throw new IllegalArgumentException("Error: already taken");
        }
        else {
            Connection conn = database.getConnection();
            database.insertUser(conn, user_to_insert);
            database.closeConnection(conn);
        }
    }

    /**
     * finds a user in the database
     * @param user_to_find username of user to find
     */
    public void findUser(User user_to_find) throws Exception {
        Connection conn = database.getConnection();
        database.findUser(conn, user_to_find);
        database.closeConnection(conn);
    }

    /**
     * Removes a chess game from the database
     * @param user_name the username of the chess player to remove
     *
     */
    public void removeUser(String user_name) throws Exception {
        Connection conn = database.getConnection();
        database.removeUser(conn, user_name);
        database.closeConnection(conn);
    }

    /**
     * lists all the users in the database
     */
    public ArrayList<User> listAllUsers() throws Exception {
        ArrayList<User> user_list = new ArrayList<User>();
        Connection conn = database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                User user = new User(username, password, email);
                user_list.add(user);
            }
        }
        return user_list;
    }

    /**
     * Clears all users from the database
     */
    public void clearAllUsers() throws Exception {
        Connection conn = database.getConnection();
        database.clearAllUsers(conn);
        database.closeConnection(conn);
    }
}
