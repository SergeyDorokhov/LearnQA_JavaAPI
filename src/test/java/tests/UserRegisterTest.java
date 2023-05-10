package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertion;
import lib.BaseTestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterTest extends BaseTestCase {

    @Test
    public void testCreateUserWithExistEmail() {
        String email = "vinkotov@example.com";
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", "123");
        data.put("username", "learnqa");
        data.put("firstName", "learnqa");
        data.put("lastName", "learnqa");
        Response response = RestAssured
                .given()
                .body(data)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();
        Assertion.asserResponseCodeEquals(response, 400);
        Assertion.asserResponseTextEquals(response
                , "Users with email '" + email + "' already exists");
    }
}
