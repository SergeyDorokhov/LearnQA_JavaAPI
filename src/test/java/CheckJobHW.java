import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckJobHW {
    @Test
    public void testRedirect() throws InterruptedException {
        Map<String, String> tokenStore = new HashMap<>();
        JsonPath response = getJsonPath(tokenStore);
        String token = response.get("token");
        int seconds = response.get("seconds");
        tokenStore.put("token", token);
        response = getJsonPath(tokenStore);
        assertEquals("Job is NOT ready", response.get("status"));
        Thread.sleep(seconds * 1000 + 1);
        response = getJsonPath(tokenStore);
        assertEquals("Job is ready", response.get("status"));
        assertNotNull(response.get("result"));
    }

    private JsonPath getJsonPath(Map<String, String> tokenStore) {
        JsonPath response;
        response = RestAssured
                .given()
                .queryParams(tokenStore)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        return response;
    }
}
