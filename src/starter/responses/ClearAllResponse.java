package responses;

/**
 * Response to clearing a database
 */
public class ClearAllResponse {

    /**
     * response message to request
     */
    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * ClearAllResponse constructor
     * @param message the response message
     */
    public ClearAllResponse(String message) {
        this.message = message;
    }
}
