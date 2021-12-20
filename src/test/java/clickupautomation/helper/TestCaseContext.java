package clickupautomation.helper;

import clickupautomation.domain.Folder;
import clickupautomation.domain.List;
import clickupautomation.domain.Task;
import cucumber.api.java.en.Then;

public class TestCaseContext {
    private static final ThreadLocal<TestCaseContext> THREAD_LOCAL =new ThreadLocal<>();

    private Folder testFolder;
    private List testList;
    private Task testTask;

    public static void init(){
        System.out.println("A new copy of TestCaseContext is now created for the particular thread");
        THREAD_LOCAL.set(new TestCaseContext());
    }

    public static TestCaseContext get(){
        return THREAD_LOCAL.get();
    }

    public Folder getTestFolder() {
        return testFolder;
    }

    public void setTestFolder(Folder testFolder) {
        this.testFolder = testFolder;
    }

    public List getTestList() {
        return testList;
    }

    public void setTestList(List testList) {
        this.testList = testList;
    }

    public Task getTestTask() {
        return testTask;
    }

    public void setTestTask(Task testTask) {
        this.testTask = testTask;
    }
}
