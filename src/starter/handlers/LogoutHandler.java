package handlers;

import com.google.gson.Gson;
import requests.LoginRequest;
import requests.LogoutRequest;
import responses.LoginResponse;
import services.UserServices;
import spark.Request;
import spark.Response;
import responses.*;
import java.util.*;

public class LogoutHandler extends ParentHandler {
    public String handle(Request spark_request, Response spark_response) {
        String header = spark_request.headers("authorization");
        Gson json_handler = new Gson();
        LogoutRequest header_request = new LogoutRequest(header);

        UserServices logout_service = new UserServices();
        LogoutResponse logout_response =  logout_service.logout(header_request);
        spark_response.status(getResponseValue(logout_response.getMessage()));

        return json_handler.toJson(logout_response);
    }
}
