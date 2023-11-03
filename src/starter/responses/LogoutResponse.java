package responses;

/**
 * Response to logging out of a server
 */
public class LogoutResponse {
    /**
     * response message to request
     */
    private String message;
    public String getMessage() {
        return message;
    }

    public LogoutResponse(String message) {
        this.message = message;
    }
}
