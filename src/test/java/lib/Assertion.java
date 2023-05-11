package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertion {
    public static void assertJsonByName(Response response, String name, int expectedValue) {
        response.then().assertThat().body("$", hasKey(name));
        int value = response.jsonPath().getInt(name);
        assertEquals(expectedValue, value, "No expected value: " + value);
    }

    public static void assertResponseTextEquals(Response response, String expectedText) {
        assertEquals(expectedText, response.asString(), "No expected text: " + expectedText);
    }

    public static void assertResponseCodeEquals(Response response, int expectedCode) {
        assertEquals(expectedCode, response.statusCode(), "No expected code: " + expectedCode);
    }

    public static void assertJsonHasKey(Response response, String expectedField) {
        response.then().assertThat().body("$", hasKey(expectedField));
    }
}
