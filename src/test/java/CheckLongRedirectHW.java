import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class CheckLongRedirectHW {
    @Test
    public void testRedirect() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();
        int statusCode = response.getStatusCode();
        String location = response.getHeader("Location");
        while (statusCode == 301) {
            response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .get(location)
                    .andReturn();
            statusCode = response.getStatusCode();
            location = response.getHeader("Location");
        }
        System.out.println(statusCode);
        System.out.println(location);
    }
}
