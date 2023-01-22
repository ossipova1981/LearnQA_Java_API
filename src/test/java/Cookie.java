import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class Cookie {


    @Test
    public void testCookie() {

        String url = "https://playground.learnqa.ru/api/homework_cookie";
        Response response = getUrl(url);

        assertFalse(response.getCookies().isEmpty(), "Empty cookies");

        Map<String, String> cookies = response.getCookies();

        Response response2 = RestAssured

                .get("https://playground.learnqa.ru/api/homework_cookie")

                .andReturn();

        assertFalse(response2.getCookies().isEmpty(), "Empty cookie");

        assertEquals(cookies, response2.getCookies(), " My Cookies");

        System.out.println(response.getCookies());



    }

    private Response getUrl(String url) {
        Response response = RestAssured
                .get(url)
                .andReturn();
        return response;
    }
}
