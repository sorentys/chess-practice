package models;

/**
 * User instance for creating and using users in chess games and in the database
 */
public class User {

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

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

