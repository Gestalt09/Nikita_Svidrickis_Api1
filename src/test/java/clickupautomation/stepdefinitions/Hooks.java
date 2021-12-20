package clickupautomation.stepdefinitions;

import clickupautomation.clients.ClickUpClient;
import clickupautomation.domain.Task;
import clickupautomation.helper.TestCaseContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static clickupautomation.constants.CommonProjectConstants.Task_Name;

public class Hooks {
    @Before
    public void beforeEachTestCase(){
        System.out.println("This is executed before each test case");
        TestCaseContext.init();
    }

}
