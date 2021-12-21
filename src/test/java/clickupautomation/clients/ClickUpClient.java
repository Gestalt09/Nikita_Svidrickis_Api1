package clickupautomation.clients;

import clickupautomation.domain.Folder;
import clickupautomation.domain.List;
import clickupautomation.domain.Task;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ClickUpClient {
    public static Folder fetchClickUp(String folder_id) {

        final ValidatableResponse responseFromClickUp = given().log().everything()
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
        final ValidatableResponse responseFromClickUpList = given().log().everything()
                .header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .when()
                .get("https://api.clickup.com/api/v2/list/" + list_id)
                .then().log().everything()
                .statusCode(200);

        List listFromRestApiCall = responseFromClickUpList.extract().response().as(List.class);

        return listFromRestApiCall;

    }
    public static String myId;
    public static Task createNewTask(String listId) {


        JSONObject jsonObj = new JSONObject()
                .put("name","New Task");

        final ValidatableResponse responseFromClickUpList = given().log().ifValidationFails()
                .header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT")
                .contentType("application/json")
                .when()
                .contentType(ContentType.JSON)
                .body(jsonObj.toString())
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().ifValidationFails()
                .statusCode(200);//200 is ok
                System.out.println(myId);
        System.out.println(responseFromClickUpList);



        Task taskFromRestApiCall= responseFromClickUpList.extract().response().as(Task.class);
        myId = responseFromClickUpList.and().contentType(ContentType.JSON).extract().path("id");

        return taskFromRestApiCall;
    }

    }
