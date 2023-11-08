package handlers;


import com.google.gson.Gson;
import requests.RegisterRequest;
import responses.RegisterResponse;
import services.UserServices;
import spark.Request;
import spark.Response;

/**
 * handles a request to register a user
 */
public class RegisterHandler extends ParentHandler{
    /**
     * handles a request to register a user
     * @param spark_request holds required information to make a Register Request
     * @param spark_response spark response to set any Register responses
     */
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
