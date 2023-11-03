package handlers;

import com.google.gson.Gson;
import requests.CreateGameRequest;
import requests.LoginRequest;
import responses.CreateGameResponse;
import responses.LoginResponse;
import services.GameServices;
import services.UserServices;
import spark.Request;
import spark.Response;

public class CreateGameHandler extends ParentHandler {
    public String handle(Request spark_request, Response spark_response) {
        String body = spark_request.body();
        String header = spark_request.headers("authorization");
        Gson json_handler = new Gson();
        CreateGameRequest full_request = json_handler.fromJson(body, CreateGameRequest.class);
        full_request.setAuthToken(header);

        GameServices login_service = new GameServices();
        CreateGameResponse create_game_response =  login_service.createGame(full_request);
        spark_response.status(getResponseValue(create_game_response.getMessage()));

        return json_handler.toJson(create_game_response);
    }
}