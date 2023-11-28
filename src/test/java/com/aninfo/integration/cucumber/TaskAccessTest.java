package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidTaskException;
import com.aninfo.exceptions.TaskNameAlreadyTaken;
import com.aninfo.model.Task;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TaskAccessTest extends TaskIntegrationServiceTest {

    private Task task;

    private Collection<Task> allTaskInProject;

    private InvalidTaskException ipe;
    private TaskNameAlreadyTaken pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Resetting system");
        ipe = null;
        deleteAll();
    }


    @Given("^A the project with the name project1$")
    public void no_project() {
    }

    @When("^Trying to get the tasks$")
    public void trying_to_get_task_in_project() {this.allTaskInProject = findAllTaskInProject(1L);}

    @Then("^I get nothing$")
    public void no_task_gotten() { assertTrue(this.allTaskInProject.isEmpty()); }


    @Given("^A the project with the name project1 with 2 tasks$")
    public void two_projects() {
        createTask("task1");
        createTask("task2");
        this.allTaskInProject =  getAllTask();
    }

    @Then("^I get two tasks$")
    public void two_projects_gotten() {assertEquals(this.allTaskInProject.size(), 2);}


    @Given("^A task in a project with name (.*)$")
    public void task_with_a_name(String name) { createTask(name);}

    @When("^Trying to find the task with name (.*)$")
    public void trying_to_find_task(String name) {
        try {

            this.task = findByName(name);
        } catch (InvalidTaskException ipe) {
            this.ipe = ipe;
        }
    }

    @Then("^I get the task appropriately$")
    public void obtains_project_appropriately() {
        assertNotNull(this.task);
        assertNull(this.pnat);
    }

    @Then("^I get an Invalid task Exception$")
    public void obtains_invalid_project_exception() {
        assertNull(this.task);
        assertNotNull(this.ipe);
    }

    @After
    public void tearDown() {
        deleteAll();
        System.out.println("After all test execution");
    }
}

