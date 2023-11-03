package handlers;

import com.google.gson.Gson;
import requests.RegisterRequest;
import responses.ClearAllResponse;
import responses.RegisterResponse;
import services.UserServices;
import spark.Request;
import spark.Response;
import services.*;

public class ClearAllHandler extends ParentHandler {
    public String handle(Response spark_response) {
        Gson json_handler = new Gson();

        GameServices game_service = new GameServices();
        ClearAllResponse clearall_response =  game_service.clearAll();
        spark_response.status(getResponseValue(clearall_response.getMessage()));

        return json_handler.toJson(clearall_response);
    }
}