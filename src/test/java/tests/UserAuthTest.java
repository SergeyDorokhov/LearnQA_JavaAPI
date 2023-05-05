package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lib.Assertion;
import lib.BaseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

public class UserAuthTest extends BaseTestCase {
    String cookie;
    String header;
    int userIdOnAuth;

    @BeforeEach
    public void loginUser() {
        Map<String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");
        Response response = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();
        this.cookie = getCookie(response, "auth_sid");
        this.header = getHeader(response, "x-csrf-token");
        this.userIdOnAuth = this.getIntFromJson(response, "user_id");
    }


    @Test
    public void testAuthUser() {
        //assertEquals(200, response.getStatusCode(), "Unexpected");
//        assertTrue(this.cookies.containsKey("auth_sid"), "No auth_sid");
//        assertTrue(headers.hasHeaderWithName("x-csrf-token"), "No token");
//        assertTrue(response.jsonPath().getInt("user_id") > 0, "Not > 0");
        Response response1 = RestAssured
                .given()
                .header("x-csrf-token", this.header)
                .cookie("auth_sid", this.cookie)
                .get("https://playground.learnqa.ru/api/user/auth")
                .andReturn();
        Assertion.asserJsonByName(response1, "user_id", this.userIdOnAuth);
    }

    @ParameterizedTest
    @ValueSource(strings = {"cookies", "headers"})
    public void testNegativeAuthUser(String condition) {
        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth");
        if (condition.equals("cookies")) {
            spec.cookie("auth_sid", this.cookie);
        } else if (condition.equals("headers")) {
            spec.header("x-csrf-token", this.header);
        } else {
            throw new IllegalArgumentException("Condition is known");
        }
        Response response1 = spec.get().andReturn();
        Assertion.asserJsonByName(response1, "user_id", 0);
    }
}
