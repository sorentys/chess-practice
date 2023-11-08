package handlers;

/**
 * handles setting the status code for spark responses
 */
public class ParentHandler {
    /**
     * decides the response value for the spark response
     * @param message the message that contains the status information
     */
    public int getResponseValue(String message) {
        int response_value = 0;
        if (message == null) {
            response_value = 200;
        } else if (message.contains("bad request")) {
            response_value = 400;
        } else if (message.contains("already taken")) {
            response_value = 403;
        } else if (!message.contains("Error")) {
            response_value = 500;
        } else if (message.contains("unauthorized")) {
            response_value = 401;
        } else {
            response_value = 200;
        }
        return response_value;
    }
}
