package requests;

/**
 * Request to login into server
 */
public class LoginRequest {
    /**
     * chess player username
     */
    private String username;

    /**
     * chess player's password
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
