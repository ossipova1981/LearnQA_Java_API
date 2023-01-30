import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHeaders {
    @Test
    public void requestHeaderMethod() {
        Map<String, String> headers = new HashMap<>();
        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_header")
                .prettyPeek()
                .andReturn();

        System.out.println("Headers:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        String secretHeader = response.getHeader("x-secret-homework-header");
        System.out.println(secretHeader);

        assertTrue(secretHeader.contentEquals("Some secret value"), " Error message");
    }
}