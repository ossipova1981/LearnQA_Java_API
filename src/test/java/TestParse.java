import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestParse {


    @Test
    public void testParse() {

            JsonPath response = RestAssured
                    .get("https://playground.learnqa.ru/api/get_json_homework")
                    .jsonPath();

            List<JsonPath> answer = response.getList("messages");
            System.out.println(answer.get(1));
    }
}

