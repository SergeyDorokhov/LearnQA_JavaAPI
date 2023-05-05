package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckCookies {
    @Test
    public void testGetCookies() {
        Map<String, Object> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");
        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();
        System.out.println("\nPretty text:");
        response.prettyPrint();
        System.out.println("\nHeaders:");
        System.out.println(response.getHeaders());
        System.out.println("\nCookies:");
        System.out.println(response.getCookies());
    }

    @Test
    public void testCheckCookies() {
        Map<String, Object> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");
        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();
        Map<String, Object> cookies = new HashMap<>();
        String authCookie = response.getCookie("auth_cookie");
        if (authCookie != null) {
            cookies.put("auth_cookie", authCookie);
        }
        Response responseForCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();
        responseForCheck.print();
    }
}
