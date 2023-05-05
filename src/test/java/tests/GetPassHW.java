package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetPassHW {

    Map<String, Object> passwordsMap = new HashMap<>();

    @Test
    public void testGetCookies() {
        Response response;
        Response responseForCheck;
        String[] passwords = {"123456", "123456789", "qwerty", "password", "1234567", "12345678"
                , "12345", "iloveyou", "111111", "123123", "abc123", "qwerty123", "1q2w3e4r"
                , "admin", "qwertyuiop", "654321", "555555", "lovely", "7777777", "welcome"
                , "888888", "princess", "dragon", "password1", "123qwe"};
        String answer = "You are NOT authorized";
        int index = 0;
        Map<String, Object> cookies = new HashMap<>();
        while (answer.equals("You are NOT authorized")) {
            passwordsMap = putPass(passwords[index]);
            response = RestAssured
                    .given()
                    .body(passwordsMap)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            String cookie = response.getCookie("auth_cookie");
            cookies.put("auth_cookie", cookie);
            responseForCheck = RestAssured
                    .given()
                    .cookies(cookies)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();
            answer = responseForCheck.print();
            if (answer.equals("You are authorized")) {
                System.out.println(passwords[index]);
            }
            index++;
        }
    }

    private Map<String, Object> putPass(String password) {
        passwordsMap.put("login", "super_admin");
        passwordsMap.put("password", password);
        return passwordsMap;
    }
}

