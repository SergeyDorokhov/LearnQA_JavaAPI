import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "John"})
    public void testHello(String name) {
        Map<String, String> params = new HashMap<>();
        if (name.length() > 0) {
            params.put("name", name);
        }
        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.get("answer");
        String expectedName = (name.length() > 0) ? name : "someone";
        System.out.println(answer);
        assertEquals("Hello, " + expectedName, answer, "Unexpected");
    }
}
