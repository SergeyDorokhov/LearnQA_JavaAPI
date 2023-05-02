import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckStatusCode {
    @Test
    public void testCheckType() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        System.out.println(response.getStatusCode());
    }
    @Test
    public void testRedirect() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(true)
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();
        System.out.println(response.getStatusCode());
    }
}
