package handlers;

import com.google.gson.Gson;
import requests.ListGamesRequest;
import responses.ListGamesResponse;
import services.GameServices;
import spark.Request;
import spark.Response;

/**
 * handles a request to list all chess games in the database
 */
public class ListGamesHandler extends ParentHandler {
    /**
     * handles a request to list all chess games in the database
     * @param spark_request holds required information to make a ListGame Request
     * @param spark_response spark response to set any ListGame responses
     */
    public String handle(Request spark_request, Response spark_response) throws Exception {
        String header = spark_request.headers("authorization");
        Gson json_handler = new Gson();
        ListGamesRequest header_request = new ListGamesRequest(header);

        GameServices list_games_service = new GameServices();
        ListGamesResponse list_games_response =  list_games_service.listGames(header_request);
        spark_response.status(getResponseValue(list_games_response.getMessage()));

        return json_handler.toJson(list_games_response);
    }
}
