package requests;

/**
 * Request to register a user or game
 */
public class RegisterRequest {
    /**
     * chess player username
     */
    private String username;

    /**
     * chess player's password
     */
    private String password;

    /**
     * chess player's email
     */
    private String email;

    public RegisterRequest() {}
}
