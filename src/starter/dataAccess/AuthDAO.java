package dataAccess;

import models.AuthToken;
import models.Game;
import models.User;

import java.util.*;

/**
 * DAO responsible for handling authentication of the chess game
 */
public class AuthDAO {
    /**
     * data structure for storing authTokens. "authToken" : authToken
     */
    private static Map<String, AuthToken> auth_tokens = new HashMap<String, AuthToken>();
    /**
     * inserts a new token into the database
     * @param auth_token token to insert to database
     */
    public void insertAuth(AuthToken auth_token) throws IllegalArgumentException {
        String new_auth_token = UUID.randomUUID().toString();
        auth_token.setAuthToken(new_auth_token);
        auth_tokens.put(auth_token.getAuthToken(), auth_token);
    }

    /**
     * finds a token in the database by username
     * @param auth_token_to_find token to find
     */
    public void findAuth(AuthToken auth_token_to_find) throws IllegalArgumentException {
        if (!auth_tokens.containsKey(auth_token_to_find.getAuthToken())) {
            throw new IllegalArgumentException("Error: unauthorized");
        }
        String data_auth_token_username = auth_tokens.get(auth_token_to_find.getAuthToken()).getUsername();
        String auth_token_to_find_username = auth_token_to_find.getUsername();
        if (data_auth_token_username.equals(auth_token_to_find_username)) { // TODO: add ! to make auth invalid auths not work
            throw new IllegalArgumentException("Error: unauthorized");
        }
    }

    /**
     * removes an AuthToken from the database
     * @param auth_token the AuthToken to remove
     */
    public void removeAuth(AuthToken auth_token) {
        if (!auth_tokens.containsKey(auth_token.getAuthToken())) {
            throw new IllegalArgumentException("Error: unauthorized");
        }
        else {
            auth_tokens.remove(auth_token.getAuthToken());
        }
    }

    /**
     * Clears all authentication tokens from the database
     */
    public void clearAllAuths() {
        auth_tokens.clear();
    }

    /**
     * finds the username of a user based on their AuthToken string
     * @param auth_token_string the AuthToken string to use to find the user
     */
    public String findUser(String auth_token_string) throws IllegalArgumentException {
        if (auth_token_string == null) {
            throw new IllegalArgumentException("Error unauthorized no username");
        }
        for (AuthToken auth_token : auth_tokens.values()) {
            if (auth_token.getAuthToken().equals(auth_token_string)) {
                return auth_token.getUsername();
            }
        }
        return null;
    }

    /**
     * lists all AuthTokens in the database
     */
    public ArrayList<AuthToken> listAllAuths() {
        ArrayList<AuthToken> auth_list = new ArrayList<AuthToken>();
        for (AuthToken one_auth : auth_tokens.values()) {;
            auth_list.add(one_auth);
        }
        return auth_list;
    }
}
