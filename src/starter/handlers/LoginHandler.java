package handlers;

import com.google.gson.Gson;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.LoginResponse;
import responses.RegisterResponse;
import services.UserServices;
import spark.Request;
import spark.Response;

public class LoginHandler extends ParentHandler {
    public String handle(Request spark_request, Response spark_response) {
        String body = spark_request.body();
        Gson json_handler = new Gson();
        LoginRequest body_request = json_handler.fromJson(body, LoginRequest.class);

        UserServices login_service = new UserServices();
        LoginResponse login_response =  login_service.login(body_request);
        spark_response.status(getResponseValue(login_response.getMessage()));

        return json_handler.toJson(login_response);
    }
}
