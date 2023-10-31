package services;
import dataAccess.*;

import dataAccess.UserDAO;
import models.User;
import requests.RegisterRequest;
import responses.RegisterResponse;

/**
 * Service for handling registration requests
 */
public class RegisterService {
    /**
     * Registers a user into the database
     * @param r a request for registering a new user
     * @return a response to the request, (failed or not failed)
     */
    public RegisterResponse register(RegisterRequest r) {
        User user_to_register = new User(r.getUsername(), r.getPassword(), r.getEmail());

        try {
            new UserDAO().insertUser(user_to_register);
        } catch (IllegalArgumentException err) {
            return new RegisterResponse(null, null, err.getMessage());
        }
        return null;
    }
}
