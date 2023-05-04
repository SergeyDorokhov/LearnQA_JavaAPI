import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class CheckLongRedirectHW {
    @Test
    public void testRedirect() {
        Response response;
        int statusCode = 301;
        int count = 0;
        String location = "https://playground.learnqa.ru/api/long_redirect";
        while (statusCode == 301) {
            response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .get(location)
                    .andReturn();
            statusCode = response.getStatusCode();
            location = response.getHeader("Location");
            count++;
        }
        System.out.println(statusCode);
        System.out.println(location);
        System.out.println("итоговое количество редиректов: " + count);
    }
}
