package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidTaskException;
import com.aninfo.model.Task;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TaskEliminationTest extends TaskIntegrationServiceTest{
    private Long taskToDelete;
    private InvalidTaskException ite;
    @Given("^Only one task named (.*)$")
    public void task_that_can_be_found(String name) {
        Task task = createTask(name);
        this.taskToDelete = task.getId();
        Task found = null;
        InvalidTaskException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidTaskException ite) {
            exception = ite;
        }

        assertNotNull(found);
        assertNull(exception);
        assertEquals(found.getName(), task.getName());
    }

    @When("^Trying to delete the task$")
    public void trying_to_delete_task() {
        deleteById(this.taskToDelete);
    }

    @Given("^A task named (.*) and another named (.*)$")
    public void projects_that_can_be_found(String name1, String name2) {
        this.taskToDelete = createTask(name1).getId();
        createTask(name2);
    }

    @When("^Trying to delete task (.*)$")
    public void trying_to_delete_one_task(String name) {
        deleteById(findByName(name).getId());
    }

    @Then("^Task (.*) can no longer be found$")
    public void cannot_find_deleted_project(String name){
        Task found = null;
        InvalidTaskException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidTaskException ite) {
            exception = ite;
        }
        assertNull(found);
        assertNotNull(exception);
    }
    @And("^Task (.*) can still be found$")
    public void can_find_other_project(String name)
    {
        Task found = null;
        InvalidTaskException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidTaskException ite) {
            exception = ite;
        }

        assertNotNull(found);
        assertNull(exception);
        assertEquals(name,found.getName());
    }

    @After
    public void beforeEachTest() {
        System.out.println("Resetting system");
        deleteAll();
    }
}
