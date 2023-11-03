package handlers;

import com.google.gson.Gson;
import requests.CreateGameRequest;
import requests.ListGamesRequest;
import requests.LogoutRequest;
import responses.CreateGameResponse;
import responses.ListGamesResponse;
import responses.LogoutResponse;
import services.GameServices;
import services.UserServices;
import spark.Request;
import spark.Response;

public class ListGamesHandler extends ParentHandler {
    public String handle(Request spark_request, Response spark_response) {
        String header = spark_request.headers("authorization");
        Gson json_handler = new Gson();
        ListGamesRequest header_request = new ListGamesRequest(header);

        GameServices list_games_service = new GameServices();
        ListGamesResponse list_games_response =  list_games_service.listGames(header_request);
        spark_response.status(getResponseValue(list_games_response.getMessage()));

        return json_handler.toJson(list_games_response);
    }
}
