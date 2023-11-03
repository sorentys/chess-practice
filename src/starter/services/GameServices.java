package services;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import models.AuthToken;
import models.Game;
import models.User;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import requests.ListGamesRequest;
import responses.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Service for managing request to clear database
 */
public class GameServices {
    /**
     * Creates a chess game
     * @param r a request for creating the game
     * @return a response to the request, (failed or not failed)
     */
    public CreateGameResponse createGame(CreateGameRequest r) {
        if (r.getGameName() == null) {
            return new CreateGameResponse(null,"Error: bad request");
        }
        if (r.getAuthToken().isEmpty()) {
            return new CreateGameResponse(null, "Error: unauthorized");
        }
        Game game = new Game(r.getGameName());
        AuthToken token = new AuthToken(r.getAuthToken(), null);
        try {
            new AuthDAO().findAuth(token);
            new GameDAO().insertGame(game);
        } catch (IllegalArgumentException err) {
            return new CreateGameResponse(null, err.getMessage());
        }
        return new CreateGameResponse(game.getGameID(), null);
    }

    /**
     * Method to join a chess game
     * @param r a request for clearing
     * @return a response to the request, (failed or not failed)
     */
    public JoinGameResponse joinGame(JoinGameRequest r) {
        if (r.getGameID() == null) {
            return new JoinGameResponse("Error: bad request");
        }
        if (r.getAuthToken().isEmpty()) {
            return new JoinGameResponse("Error: unauthorized");
        }
        AuthToken token = new AuthToken(r.getAuthToken(), null);
        try {
            new GameDAO().findGame(r.getGameID());
            new AuthDAO().findAuth(token);
        } catch (IllegalArgumentException err) {
            return new JoinGameResponse(err.getMessage());
        }
        if (r.getPlayerColor() == null) {
            return new JoinGameResponse(null);
        }
        try {
            String user_to_join = new AuthDAO().findUser(r.getAuthToken());
            GameDAO join_game_dao = new GameDAO();
            Game game_to_join = join_game_dao.findGame(r.getGameID());
            join_game_dao.claimSpotInGame(r.getPlayerColor(), user_to_join, game_to_join);
        } catch (IllegalArgumentException err) {
            return new JoinGameResponse(err.getMessage());
        }
        return new JoinGameResponse(null);
    }

    /**
     * Lists all games in the database
     * @param r a request for listing the games
     * @return a response to the request, (failed or not failed)
     */
    public ListGamesResponse listGames(ListGamesRequest r) {
        if (r.getAuthToken() == null) {
            return new ListGamesResponse(null, "Error: bad request");
        }
        AuthToken token = new AuthToken(r.getAuthToken(), null);
        ArrayList<Game> games_list;
        try {
            new AuthDAO().findAuth(token);
            games_list = (new GameDAO().listAllGames());
        } catch (IllegalArgumentException err) {
            return new ListGamesResponse(null, err.getMessage());
        }
        return new ListGamesResponse(games_list, null);
    }

    /**
     * clears the entire database
     * @return a response to the request, (failed or not failed)
     */
    public ClearAllResponse clearAll() {
        new UserDAO().clearAllUsers();
        new AuthDAO().clearAllAuths();
        new GameDAO().clearAllGames();
        return new ClearAllResponse(null);
    }
}
