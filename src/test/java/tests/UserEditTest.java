package tests;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static lib.ApiCoreRequests.*;

@DisplayName("Редактирование пользователя")
class UserEditTest extends BaseTestCase {

    @Test
    void testEditWithoutAuth() {
        String newName = "new name";
        Map<String, String> editData = new HashMap<>();
        editData.put("username", newName);
        Response responseEditUser = putEditUserRequest(
                "", "", editData, "2");
        Assertions.assertResponseTextEquals(responseEditUser,
                "Auth token not supplied");

        Response userDataResponse = getUserDataRequest("", "", "2");
        Assertions.assertJsonByName(userDataResponse, "username", "Vitaliy");
    }

    @Test
    void testEditWithAnotherUser() {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        generateUserRequest(userData);
        Response responseGetAuthForEdit = authRequest("vinkotov@example.com", "1234");
        Response responseGetAuthAnother = authRequest(userData.get("email"), userData.get("password"));

        String newName = "Change name";
        Map<String, String> editData = new HashMap<>();
        editData.put("firstName", newName);

        Response responseEditUser = putEditUserRequest(
                this.getHeader(responseGetAuthAnother, "x-csrf-token"),
                this.getCookie(responseGetAuthAnother, "auth_sid"),
                editData, "2");
        responseEditUser.prettyPrint();

        Response userDataResponse = getUserDataRequest(
                this.getHeader(responseGetAuthForEdit, "x-csrf-token"),
                this.getCookie(responseGetAuthForEdit, "auth_sid"),
                "2");
        Assertions.assertJsonByName(userDataResponse, "firstName", "Vitalii");
    }

    @Test
    void testEditWithWrongEmail() {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        JsonPath responseCreateAuth = generateUserRequest(userData);
        String userId = responseCreateAuth.getString("id");
        String userEmail = userData.get("email");

        Response responseGetAuth = authRequest(userEmail, userData.get("password"));
        Map<String, String> editData = new HashMap<>();
        editData.put("email", "test.test.ru");

        Response responseEditUser = putEditUserRequest(
                this.getHeader(responseGetAuth, "x-csrf-token"),
                this.getCookie(responseGetAuth, "auth_sid"),
                editData, userId);
        Assertions.assertResponseTextEquals(responseEditUser,
                "Invalid email format");

        Response userDataResponse = getUserDataRequest(
                this.getHeader(responseGetAuth, "x-csrf-token"),
                this.getCookie(responseGetAuth, "auth_sid"),
                userId);
        Assertions.assertJsonByName(userDataResponse, "email", userEmail);
    }

    @Test
    void testEditFirstNameTooShort() {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        JsonPath responseCreateAuth = generateUserRequest(userData);
        String userId = responseCreateAuth.getString("id");
        Response responseGetAuth = authRequest(userData.get("email"), userData.get("password"));

        String newName = "A";
        Map<String, String> editData = new HashMap<>();
        editData.put("firstName", newName);

        Response responseEditUser = putEditUserRequest(
                this.getHeader(responseGetAuth, "x-csrf-token"),
                this.getCookie(responseGetAuth, "auth_sid"),
                editData, userId);
        Assertions.assertJsonByName(responseEditUser, "error",
                "Too short value for field firstName");

        Response responseUserData = getUserDataRequest(
                this.getHeader(responseGetAuth, "x-csrf-token"),
                this.getCookie(responseGetAuth, "auth_sid"),
                userId);

        Assertions.assertJsonByName(responseUserData, "firstName", userData.get("firstName"));
    }
}


