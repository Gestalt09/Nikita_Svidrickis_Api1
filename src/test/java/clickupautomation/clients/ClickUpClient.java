package clickupautomation.clients;

import clickupautomation.domain.Folder;
import clickupautomation.domain.List;
import clickupautomation.domain.Task;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

public class ClickUpClient {
    public static Folder fetchClickUp(String folder_id) {

        final ValidatableResponse responseFromClickUp = RestAssured
                .given().log().everything()
                // .param("token","pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .when()
                .get("https://api.clickup.com/api/v2/folder/" + folder_id)
                .then().log().everything()
                .statusCode(200);


        Folder folderFromRestApiCall = responseFromClickUp.extract().response().as(Folder.class);

        return folderFromRestApiCall;
    }

    public static List fetchClickUpList(String list_id) {
        final ValidatableResponse responseFromClickUpList = RestAssured
                .given().log().everything()
                .header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .when()
                .get("https://api.clickup.com/api/v2/list/" + list_id)
                .then().log().everything()
                .statusCode(200);

        List listFromRestApiCall = responseFromClickUpList.extract().response().as(List.class);

        return listFromRestApiCall;

    }

    public static Task createNewTask(String listId) {

        JSONObject jsonObj = new JSONObject()
                .put("name","New Task");

        final ValidatableResponse responseFromClickUpList = RestAssured
                .given().log().ifValidationFails()
                .header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .contentType("application/json")
                .body(jsonObj.toString())
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().ifValidationFails()
                .statusCode(200);//200 is ok

        Task taskFromRestApiCall= responseFromClickUpList.extract().response().as(Task.class);

        return taskFromRestApiCall;
    }


    }
