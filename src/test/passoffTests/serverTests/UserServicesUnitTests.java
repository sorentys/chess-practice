package passoffTests.serverTests;

import models.AuthToken;
import models.User;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import requests.LogoutRequest;
import requests.RegisterRequest;
import responses.LoginResponse;
import responses.LogoutResponse;
import responses.RegisterResponse;
import services.*;
import dataAccess.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to instantiate unit tests for user services
 */
public class UserServicesUnitTests {
    private UserServices user_service = new UserServices();


    @Test
    public void registerPositiveTest() {
        RegisterRequest good_request = new RegisterRequest("good_user_name", "test_password", "test@email.coom");
        RegisterResponse good_response = user_service.register(good_request);

        //make sure required registration information is in the database
        assertFalse(good_response.getAuthToken() == null);
        assertFalse(good_response.getUsername() == null);
        new UserDAO().removeUser("good_user_name");
    }

    @Test
    public void registerNegativeTest() {
        RegisterRequest bad_request = new RegisterRequest("good_user_name", "test_password", null);
        RegisterResponse bad_response = user_service.register(bad_request);

        //no email should error
        String response_message = bad_response.getMessage();
        assertTrue(response_message.contains("Error"), "response should error");
    }

    @Test
    public void loginPositiveTest() {
        User good_user = new User("good_user_name", "good_password", "good@email.com");
        new UserDAO().insertUser(good_user);
        LoginRequest good_request = new LoginRequest("good_user_name", "good_password");
        LoginResponse good_response = user_service.login(good_request);

        //make sure required login information is in the database
        assertFalse(good_response.getAuthToken() == null);
        assertFalse(good_response.getUsername() == null);
        new UserDAO().removeUser("good_user_name");
    }

    @Test
    public void loginNegativeTest() {
        User good_user = new User("good_user_name", "good_password", "good@email.com");
        new UserDAO().insertUser(good_user);
        LoginRequest good_request = new LoginRequest("good_user_name", "bad_password");
        LoginResponse good_response = user_service.login(good_request);

        //a bad password should error and result in no information saved
        assertTrue(good_response.getAuthToken() == null);
        assertTrue(good_response.getUsername() == null);
        new UserDAO().removeUser("good_user_name");
    }

    @Test
    public void logoutPositiveTest() {
        RegisterRequest good_register_request = new RegisterRequest("good_user_name", "test_password", "test@email.coom");
        RegisterResponse good_register_response = user_service.register(good_register_request);

        LogoutRequest good_logout_request = new LogoutRequest(good_register_response.getAuthToken());
        LogoutResponse good_logout_response = user_service.logout(good_logout_request);

        //make sure user logged out successfully
        assertTrue(good_logout_response.getMessage() == null);
        new UserDAO().removeUser("good_user_name");
    }

    @Test
    public void logoutNegativeTest() {
        LogoutRequest bad_logout_request = new LogoutRequest("bad_auth_token");
        LogoutResponse bad_logout_response = user_service.logout(bad_logout_request);

        //a bad AuthToken should error
        String response_message = bad_logout_response.getMessage();
        assertTrue(response_message.contains("Error"), "response should error");
    }
}
