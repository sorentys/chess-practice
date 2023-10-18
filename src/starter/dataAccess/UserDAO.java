package dataAccess;

import models.AuthToken;
import models.User;

import java.util.ArrayList;

/**
 * DAO for User services
 */
public class UserDAO {
    /**
     * Adds a user to the database
     * @param user_name username for user to add
     */
    public void insertUser(String user_name) {}

    /**
     * finds a user in the database
     * @param user_name username of user to find
     * @return a user
     */
    public AuthToken findUser(String user_name) {return null;}

    /**
     * Returns all users in a database
     * @return a list of users
     */
    public ArrayList<User> findAllAuths() {return null;}
}
