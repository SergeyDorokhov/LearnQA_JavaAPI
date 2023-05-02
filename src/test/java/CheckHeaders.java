import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckHeaders {

    @Test
    public void testHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Param1", "Value1");
        headers.put("Param2", "Value2");
        Response response = RestAssured
                .given()
                .headers(headers)
                .get("https://playground.learnqa.ru/api/show_all_headers")
                .andReturn();
        response.prettyPrint();

        Headers respHeaders = response.getHeaders();
        System.out.println(respHeaders);
    }

    @Test
    public void testRedirect() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();
        System.out.println(response.getHeader("Location"));
    }
}
