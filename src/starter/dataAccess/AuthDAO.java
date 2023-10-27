package dataAccess;

import models.AuthToken;
import models.Game;

import java.util.ArrayList;

/**
 * DAO responsible for handling authentication of the chess game
 */
public class AuthDAO {
    /**
     * inserts a new token into the database
     * @param auth_token token to insert to database
     */
    public void insertAuth(AuthToken auth_token) {}

    /**
     * finds a game in the database by gameID
     * @param auth_token id of the chess game
     * @return a authToken
     */
    public AuthToken findAuth(AuthToken auth_token) {return null;}

    /**
     * Returns all the authTokens in the database
     * @return a list of chess games
     */
    public ArrayList<AuthToken> findAllAuths() {return null;}

    /**
     * Updates a user in the database
     * @param auth_token the authentication token to update
     * @return a chess game string of the updated game
     */
    public String updateAuth(AuthToken auth_token) {return null;}

    /**
     * Removes a chess game from the database
     * @param auth_token the authentication token to remove
     *
     */
    public void removeAuth(AuthToken auth_token) {}

    /**
     * Clears all authentication tokens from the database
     */
    public void clearAllAuths() {}

}
