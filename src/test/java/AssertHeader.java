import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertHeader {
    @Test
    public void testAssertCookies() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();
        assertEquals("Some secret value"
                , response.getHeader("x-secret-homework-header"), "Unexpected Header");
    }
}
