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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    /**
     * RegisterRequest constructor
     * @param username username of user to register
     * @param password password to connect to username
     * @param email user's email
     */
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
