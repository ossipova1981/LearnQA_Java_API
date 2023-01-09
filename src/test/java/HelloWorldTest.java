import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

    private Object String;

    @Test
    public void testRestAsseredCookie() {
        Map<String, String> data = new HashMap<>();

        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        System.out.println("\nPretty text:");
        response.prettyPrint();

        System.out.println("\nHeaders:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        System.out.println("\nCookies:");
        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);

        //  JsonPath j = new JsonPath();


     //   int s = j.getInt("Location.size()");
    //    for (int i = 0; i < s; i++) {
    /*        String state = j.getString("Location[" + i + "].State");
            String zip = j.getString("Location[" + i + "].zip");
            System.out.println(state);
            System.out.println(zip);*/

        }
  //  @Test


    }

 /*Object obj = new JSONParser().parse(jsonString); // Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
 // Кастим obj в JSONObject
 JSONObject jo = (JSONObject) obj;
 // Достаём firstName and lastName
 String firstName = (String) jo.get("firstName");
 String lastName = (String) jo.get("lastName");
 System.out.println("fio: " + firstName + " " + lastName);
 // Достаем массив номеров
 JSONArray phoneNumbersArr = (JSONArray) jo.get("phoneNumbers");
 Iterator phonesItr = phoneNumbersArr.iterator();
 System.out.println("phoneNumbers:");
 // Выводим в цикле данные массива
 while (phonesItr.hasNext()) {
     JSONObject test = (JSONObject) phonesItr.next();
     System.out.println("- type: " + test.get("type") + ", phone: " + test.get("number"));
 }
        @Test
        public void testHelloWorld(){
            Response responce = RestAssured
                    .get("https://playground.learnqa.ru/api/get_json_homework")
                    .andReturn();
            responce.prettyPrint();

        }

        @Test
        public static Response doGetRequest(String endpoint) {
            RestAssured.defaultParser = Parser.JSON;

            return
                    given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                            when().get(endpoint).
                            then().contentType(ContentType.JSON).extract().response();
        }

    public static void main(String[] args) {
        Response response = doGetRequest("https://playground.learnqa.ru/api/get_json_homework");

        List<String> jsonResponse = response.jsonPath().getList("$");

        System.out.println(jsonResponse);
    }
    }
*/
