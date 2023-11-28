package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.TaskNameAlreadyTaken;
import com.aninfo.model.Task;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

public class TaskCreationTest extends TaskIntegrationServiceTest {
    private Task task;
    private TaskNameAlreadyTaken pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Resetting system");
        deleteAll();
    }

    @Given("^No tasks$")
    public void una_tarea() {
    }



    @When("^Try to create a task with name (.*)$")
    public void trying_create_task(String name) {
        try {
            this.task = createTask(name);
        } catch (TaskNameAlreadyTaken pnat) {
            this.pnat = pnat;
        }
    }

    @Then("^The task is created successfully$")
    public void it_is_created_successfully() {
        assertNull(this.pnat);
        assertNotNull(this.task);

    }
    @And("^Task is named (.*)$")
    public void task_is_named(String name) {
        assertEquals(name,this.task.getName());
    }

    @Then("^It is not created because a task with that name already exists$")
    public void it_is_not_created_successfully() {
        assertNotNull(this.pnat);
        assertNull(this.task);
    }

    @After
    public void tearDown_() {
        System.out.println("After all test execution");
        deleteAll();
    }
}

