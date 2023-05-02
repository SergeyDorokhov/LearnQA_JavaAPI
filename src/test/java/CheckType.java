import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckType {
    @Test
    public void testCheckType() {
        Response response = RestAssured
                .given()
                .queryParam("param1","value1")
                .queryParam("param2","value2")
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();

        Response response1 = RestAssured
                .given()
                .body("param1=value1&param2=value2")
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response1.print();

        Map<String, Object> body = new HashMap<>();
        body.put("Param1", "Val1");
        body.put("Param2", "Val2");
        Response response3 = RestAssured
                .given()
                .body(body)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response3.print();
    }
}
