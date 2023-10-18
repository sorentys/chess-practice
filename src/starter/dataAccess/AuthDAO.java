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
    public void insertAuth(String auth_token) {}

    /**
     * finds a game in the database by gameID
     * @param auth_token id of the chess game
     * @return a authToken
     */
    public AuthToken findAuth(String auth_token) {return null;}

    /**
     * Returns all the authTokens in the database
     * @return a list of chess games
     */
    public ArrayList<AuthToken> findAllAuths() {return null;}


}
