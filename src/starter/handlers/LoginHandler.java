package handlers;

import com.google.gson.Gson;
import requests.LoginRequest;
import requests.RegisterRequest;
import responses.LoginResponse;
import responses.RegisterResponse;
import services.UserServices;
import spark.Request;
import spark.Response;

/**
 * handles a request to login a user
 */
public class LoginHandler extends ParentHandler {
    /**
     * handles a request to login to a user
     * @param spark_request holds required information to make a Login Request
     * @param spark_response spark response to set any Login responses
     */
    public String handle(Request spark_request, Response spark_response) throws Exception {
        String body = spark_request.body();
        Gson json_handler = new Gson();
        LoginRequest body_request = json_handler.fromJson(body, LoginRequest.class);

        UserServices login_service = new UserServices();
        LoginResponse login_response =  login_service.login(body_request);
        spark_response.status(getResponseValue(login_response.getMessage()));

        return json_handler.toJson(login_response);
    }
}
