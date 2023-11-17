package services;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import models.AuthToken;
import models.User;
import responses.*;
import requests.*;

/**
 * Service for handling registration requests
 */
public class UserServices {
    /**
     * Registers a user into the database
     * @param r a request for registering a new user
     * @return a response to the request, (failed or not failed)
     */
    public RegisterResponse register(RegisterRequest r) throws Exception {
        if (r.getUsername() == null | r.getPassword() == null | r.getEmail() == null) {
            return new RegisterResponse(null, null, "Error: bad request");
        }
        User user_to_register = new User(r.getUsername(), r.getPassword(), r.getEmail());
        AuthToken token = new AuthToken(null, user_to_register.getUsername());
        try {
            new UserDAO().insertUser(user_to_register);
            new AuthDAO().insertAuth(token);
        } catch (IllegalArgumentException err) {
            return new RegisterResponse(null, null, err.getMessage());
        }
        return new RegisterResponse(user_to_register.getUsername(), token.getAuthToken(), null);
    }

    /**
     * Logs a user in the chess game server
     * @param r a request for logging into the server
     * @return a response to the request, (failed or not failed)
     */
    public LoginResponse login(LoginRequest r) throws Exception {
        if (r.getUsername().isEmpty() | r.getPassword().isEmpty()) {
            return new LoginResponse(null, null, "Error: bad request");
        }
        User user_to_login = new User(r.getUsername(), r.getPassword(), null);
        AuthToken token = new AuthToken(null, user_to_login.getUsername());
        try {
            new UserDAO().findUser(user_to_login);
            new AuthDAO().insertAuth(token);
        } catch (IllegalArgumentException err) {
            return new LoginResponse(null, null, err.getMessage());
        }
        return new LoginResponse(user_to_login.getUsername(), token.getAuthToken(), null);
    }

    /**
     * Logs a user out of the chess game server
     * @param r a request for logging out
     * @return a response to the request, (failed or not failed)
     */
    public LogoutResponse logout(LogoutRequest r) throws Exception {
        if (r.getAuthToken() == null | r.getAuthToken().isEmpty() | r.getAuthToken().equals("none")) {
            return new LogoutResponse("Error: bad request");
        }
        AuthToken token = new AuthToken(r.getAuthToken(),null);
        try {
            new AuthDAO().findAuth(token);
            new AuthDAO().removeAuth(token);
        } catch (IllegalArgumentException err) {
            return new LogoutResponse(err.getMessage());
        }
        return new LogoutResponse(null);
    }
}
