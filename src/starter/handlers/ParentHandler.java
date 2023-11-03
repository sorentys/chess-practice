package handlers;

public class ParentHandler {
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
