package services;

import requests.LogoutRequest;
import responses.LoginResponse;

/**
 * Service for handling logout requests from a chess game server
 */
public class LogoutService {
    /**
     * Logs a user out of the chess game server
     * @param r a request for logging out
     * @return a response to the request, (failed or not failed)
     */
    public LoginResponse logout(LogoutRequest r) {return null;}
}
