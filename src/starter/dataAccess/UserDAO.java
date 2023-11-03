package dataAccess;

import models.AuthToken;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO for User services
 */
public class UserDAO {
    private static Map<String, User> users = new HashMap<String, User>();
    /**
     * Adds a user to the database
     * @param user_to_insert user to add
     */
    public void insertUser(User user_to_insert) throws IllegalArgumentException {
        if (users.containsKey(user_to_insert.getUsername())) {
            throw new IllegalArgumentException("Error: already taken");
        }
        else {
            users.put(user_to_insert.getUsername(), user_to_insert);
        }
    }

    /**
     * finds a user in the database
     * @param user_to_find username of user to find
     */
    public void findUser(User user_to_find) throws IllegalArgumentException {
        if (!users.containsKey(user_to_find.getUsername())) {
            throw new IllegalArgumentException("Error: unauthorized");
        }
        String data_user_password = users.get(user_to_find.getUsername()).getPassword();
        String user_to_find_password = user_to_find.getPassword();
        if (!data_user_password.equals(user_to_find_password)) {
            throw new IllegalArgumentException("Error: unauthorized");
        }
    }

    /**
     * Returns all users in a database
     * @return a list of users
     */
    public ArrayList<User> findAllUsers() {return null;}

    /**
     * Updates a user in the database
     * @param user_name the username of the chess player to update
     * @return a chess game string of the updated game
     */
    public String updateUser(String user_name) {return null;}

    /**
     * Removes a chess game from the database
     * @param user_name the username of the chess player to remove
     *
     */
    public void removeUser(String user_name) {}

    /**
     * Clears all users from the database
     */
    public void clearAllUsers() {
        users.clear();
    }
}
