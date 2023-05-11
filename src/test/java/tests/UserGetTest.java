package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertion;
import lib.BaseTestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UserGetTest extends BaseTestCase {


    @Test
    public void testGetUserDataWithAuth() {
        Map<String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");
        Response response = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();
        String cookie = getCookie(response, "auth_sid");
        String header = getHeader(response, "x-csrf-token");
        Response responseData = RestAssured
                .given()
                .cookie("auth_sid", cookie)
                .header("x-csrf-token", header)
                .get("https://playground.learnqa.ru/api/user/2")
                .andReturn();
        String[] expectedFields = {"username", "firstName", "lastName", "email"};
        Assertion.assertJsonHasFields(responseData, expectedFields);
    }

    @Test
    public void testGetUserDataNotAuth() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/user/2")
                .andReturn();
        Assertion.assertJsonHasField(response, "username");
        Assertion.assertJsonHasNotField(response, "firstName");
        Assertion.assertJsonHasNotField(response, "lastName");
        Assertion.assertJsonHasNotField(response, "email");
    }
}
