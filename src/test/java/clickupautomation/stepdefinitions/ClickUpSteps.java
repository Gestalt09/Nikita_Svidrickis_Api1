package clickupautomation.stepdefinitions;

import clickupautomation.clients.ClickUpClient;
import clickupautomation.domain.Folder;
import clickupautomation.domain.List;
import clickupautomation.domain.Task;
import clickupautomation.helper.TestCaseContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;

import static clickupautomation.constants.CommonProjectConstants.*;

public class ClickUpSteps {



    @Given("Fetch click up folder by id {string}")
    public void fetchClickUpFolderByName(String folderID) {
        System.out.println("Folder ID which comes from feature file: " + folderID);
        Folder folderFetchedFromClickUp = ClickUpClient.fetchClickUp(folderID);

        TestCaseContext.get().setTestFolder(folderFetchedFromClickUp);

        String actualFolderId = folderFetchedFromClickUp.getId();
        String actualFolderName = folderFetchedFromClickUp.getName();


        Assertions
                .assertThat(actualFolderId)
                .as("Here we are validating if actual folder id is the same as expected folder id")
                .isEqualTo(folderID);
        Assertions
                .assertThat(actualFolderName)
                .as("Here we are validating if actual folder name is the same as expected folder name")
                .isEqualTo(Folder_Name);

    }

    @When("fetch click up list by id {string}")
    public void fetchClickUpListById(String listID) {
        System.out.println("List ID which comes from feature file: " + listID);
        List listFetchedFromClickUp = ClickUpClient.fetchClickUpList(listID);

        String actualListName = listFetchedFromClickUp.getName();

        Assertions
                .assertThat(actualListName)
                .as("Here we are validating if actual list name is the same as expected list name")
                .isEqualTo(List_Name);

    }
    @Then("Create a new Task in the List")
    public void createANewTaskInTheList() {
        String listId = "150321048";



Task taskFetchedFromClickUp = ClickUpClient.createNewTask(listId);
        String actualTaskName = taskFetchedFromClickUp.getName();

        Assertions
                .assertThat(actualTaskName)
                .as("Here we are validating if actual task name is the same as expected task name")
                .isEqualTo(Task_Name);
    }


}
