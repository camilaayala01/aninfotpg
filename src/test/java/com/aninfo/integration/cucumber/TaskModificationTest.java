package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.TaskNameAlreadyTaken;
import com.aninfo.model.Status;
import com.aninfo.model.Task;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TaskModificationTest extends TaskIntegrationServiceTest  {
    private Task task1;
    private Task task2;

    private TaskNameAlreadyTaken tnat;

    @Given("^Two tasks with names (.*) and (.*)$")
    public void two_tasks(String name1, String name2) {
        this.task1 = createTask(name1);
        this.task2 = createTask(name2);

    }
    @When("^Trying to modified the task (.*) name to (.*)$")
    public void trying_modify_task_name(String name1, String name2) {
        try {
            this.task2 = editTask(findByName(name1).getId(),name2);
        } catch (TaskNameAlreadyTaken tnat) {
            this.tnat = tnat;
        }

    }
    @Then("^Name task cannot be modified$")
    public void task_name_is_not_changed(){
        assertNotNull(this.tnat);
        assertNotEquals(this.task1.getName(), this.task2.getName());
        deleteAll();
    }

    @Then("^Name task is changed successfully to (.*)$")
    public void task_name_is_changed(String name){
        assertNull(this.tnat);
        assertEquals(name, this.task2.getName());
        deleteAll();
    }

    @Given("^A task named (.*) with estimated finish date (.*)$")

    public void task_with_finish_date(String name, String finishDate) {
        this.task1 = createTask(name, LocalDate.parse(finishDate));
    }

    @When("^Trying to change the estimated finish date to (.*)$")
    public void trying_modify_task_estimated_finish_date(String finishDate) {
        try {
            this.task1 = editTask(this.task1.getId(), LocalDate.parse(finishDate), this.task1.getName());
        } catch (TaskNameAlreadyTaken tnat) {
            this.tnat = tnat;
        }

    }

    @Then("^Estimated finish date is changed successfully to (.*)$")
    public void estimated_finish_date_is_changed(String estimatedFinishDate){
        assertNull(this.tnat);
        assertEquals(LocalDate.parse(estimatedFinishDate), this.task1.getFinishDate());
        deleteAll();
    }

    @Given("^A task named (.*) with description (.*)$")
    public void task_with_des(String name, String description) {
        this.task1 = createTask(name, description);
    }

    @When("^Trying to modified the description to (.*)$")
    public void trying_modify_task_description(String description) {
        try {
            this.task1 = editTask(this.task1.getId(), description, this.task1.getName());
        } catch (TaskNameAlreadyTaken tnat) {
            this.tnat = tnat;
        }
    }

    @Then("^Description is modified successfully to (.*)$")
    public void task_description_is_changed(String description){
        assertNull(this.tnat);
        assertEquals(description, this.task1.getDescription());
        deleteAll();
    }
    @Given("^An existent task with name (.*)$")
    public void task_with_a_name(String name) {
        this.task1 = createTask(name);
    }

    @When("^Trying to modify the status to (.*)$")
    public void trying_modify_task_status(String status) {
        try {
            this.task1 = editTask(this.task1.getId(), Status.valueOf(status), this.task1.getName());
        } catch (TaskNameAlreadyTaken tnat) {
            this.tnat = tnat;
        }
    }

    @Then("^Status is modified successfully to (.*)$")
    public void task_state_is_changed(String status){
        assertNull(this.tnat);
        assertEquals(Status.valueOf(status), this.task1.getStatus());
        deleteAll();
    }

    @After
    public void tearDown() {
        deleteAll();
        System.out.println("After all test execution");
    }

}
