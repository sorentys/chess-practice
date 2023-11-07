package passoffTests.serverTests;
import models.AuthToken;
import models.Game;
import models.User;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import requests.ListGamesRequest;
import responses.CreateGameResponse;
import responses.JoinGameResponse;
import responses.ListGamesResponse;
import services.*;
import dataAccess.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class GameServicesUnitTests {
    private GameServices game_service = new GameServices();
    @Test
    public void createGamePositiveTest() {
        AuthToken auth_token = new AuthToken("unit_token", "");
        new AuthDAO().insertAuth(auth_token);
        CreateGameRequest good_request = new CreateGameRequest(auth_token.getAuthToken(), "unit_test_game");
        CreateGameResponse good_response = game_service.createGame(good_request);

        assertFalse(good_response.getGameID() == null);
        new AuthDAO().removeAuth(auth_token);
    }

    @Test
    public void createGameNegativeTest() {
        AuthToken auth_token = new AuthToken("bad_unit_token", "");
        CreateGameRequest bad_request = new CreateGameRequest(auth_token.getAuthToken(), "bad_unit_test_game");
        CreateGameResponse bad_response = game_service.createGame(bad_request);

        String response_message = bad_response.getMessage();
        assertTrue(response_message.contains("Error"), "response should error");
    }
    @Test
    public void joinGamePositiveTest() {
        AuthToken auth_token = new AuthToken("unit_token", "test_user");
        new AuthDAO().insertAuth(auth_token);
        Game chess_game = new Game("test_game");
        new GameDAO().insertGame(chess_game);
        JoinGameRequest good_request = new JoinGameRequest("WHITE", chess_game.getGameID(), auth_token.getAuthToken());
        JoinGameResponse good_response = game_service.joinGame(good_request);

        assertFalse(chess_game.getWhiteUsername().isEmpty());
        new GameDAO().removeGame(chess_game);
        new AuthDAO().removeAuth(auth_token);
    }

    @Test
    public void joinGameNegativeTest() {
        AuthToken auth_token = new AuthToken("bad_unit_token", "");
        Game chess_game = new Game("test_game");
        new GameDAO().insertGame(chess_game);
        JoinGameRequest bad_request = new JoinGameRequest("WHITE", chess_game.getGameID(), auth_token.getAuthToken());
        JoinGameResponse bad_response = game_service.joinGame(bad_request);

        String response_message = bad_response.getMessage();
        assertTrue(response_message.contains("Error"), "response should error");
        new GameDAO().removeGame(chess_game);
    }

    @Test
    public void listGamePositiveTest() {
        AuthToken auth_token = new AuthToken("unit_token", "");
        new AuthDAO().insertAuth(auth_token);
        CreateGameRequest game_1_request = new CreateGameRequest(auth_token.getAuthToken(), "game_1");
        CreateGameRequest game_2_request = new CreateGameRequest(auth_token.getAuthToken(), "game_2");
        CreateGameRequest game_3_request = new CreateGameRequest(auth_token.getAuthToken(), "game_3");
        game_service.createGame(game_1_request);
        game_service.createGame(game_2_request);
        game_service.createGame(game_3_request);
        ListGamesRequest good_request = new ListGamesRequest(auth_token.getAuthToken());
        ListGamesResponse good_response = game_service.listGames(good_request);

        assertTrue(good_response.getGames().size() == 3);
        new AuthDAO().removeAuth(auth_token);
    }

    @Test
    public void listGameNegativeTest() {
        AuthToken auth_token = new AuthToken("bad_unit_token", "");
        ListGamesRequest bad_request = new ListGamesRequest(auth_token.getAuthToken());
        ListGamesResponse bad_response = game_service.listGames(bad_request);

        String response_message = bad_response.getMessage();
        assertTrue(response_message.contains("Error"), "response should error");
    }

    @Test
    public void clearAllPositiveTest() {
        game_service.clearAll();
        ArrayList<AuthToken> auths = new AuthDAO().listAllAuths();
        ArrayList<Game> games = new GameDAO().listAllGames();
        ArrayList<User> users = new UserDAO().listAllUsers();
        assertTrue(auths.size() == 0);
        assertTrue(games.size() == 0);
        assertTrue(users.size() == 0);
    }
}
