package clickupautomation.stepdefinitions;

import clickupautomation.clients.ClickUpClient;
import clickupautomation.domain.Task;
import clickupautomation.helper.TestCaseContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

import static clickupautomation.constants.CommonProjectConstants.Task_Name;

public class Hooks {
    @Before
    public void beforeEachTestCase(){
        System.out.println("This is executed before each test case");
        TestCaseContext.init();
    }
    @After
    public void  deleteCreatedTask(){
        //String taskId = "1y232xf";


        RestAssured.baseURI = "https://app.clickup.com/24325375/v/l/li/150321048";
    final  RequestSpecification request = RestAssured
                .given();
        request.header("Authorization", "pk_36345775_PIOMFPAZ5QZO5C5N7AQ6VODNH1CMN2OT");
        request.contentType("application/json");

        Response response = request.delete("https://api.clickup.com/api/v2/task/" + ClickUpClient.myId);

        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 204);



    }
}
