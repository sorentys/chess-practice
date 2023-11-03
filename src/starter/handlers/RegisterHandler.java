package handlers;


import com.google.gson.Gson;
import requests.RegisterRequest;
import responses.RegisterResponse;
import services.UserServices;
import spark.Request;
import spark.Response;

/**
 * parses body from REQ and parse json into a RegisterRequest
 * call RegisterService.register() (which works with DAO to make a user in the map) and saves result
 * returns serialized tojson version of result
 */
public class RegisterHandler extends ParentHandler{
    public String handle(Request spark_request, Response spark_response) {
        String body = spark_request.body();
        Gson json_handler = new Gson();
        RegisterRequest body_request = json_handler.fromJson(body, RegisterRequest.class);

        UserServices register_service = new UserServices();
        RegisterResponse register_response =  register_service.register(body_request);
        spark_response.status(getResponseValue(register_response.getMessage()));

        return json_handler.toJson(register_response);
    }
}
