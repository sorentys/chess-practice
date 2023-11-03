package handlers;

import com.google.gson.Gson;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import responses.CreateGameResponse;
import responses.JoinGameResponse;
import services.GameServices;
import spark.Request;
import spark.Response;

public class JoinGameHandler extends ParentHandler{
    public String handle(Request spark_request, Response spark_response) {
        String body = spark_request.body();
        String header = spark_request.headers("authorization");
        Gson json_handler = new Gson();
        JoinGameRequest full_request = json_handler.fromJson(body, JoinGameRequest.class);
        full_request.setAuthToken(header);

        GameServices join_game_service = new GameServices();
        JoinGameResponse join_game_response =  join_game_service.joinGame(full_request);
        spark_response.status(getResponseValue(join_game_response.getMessage()));

        return json_handler.toJson(join_game_response);
    }
}
