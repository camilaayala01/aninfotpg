package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import com.aninfo.model.Status;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectModificationTest extends ProjectIntegrationServiceTest  {
    private Project project1;
    private Project project2;

    private ProjectNameAlreadyTakenException pnat;

    @Given("^Two projects with names (.*) and (.*)$")
    public void two_projects(String name1, String name2) {
        this.project1 = createProject(name1);
        this.project2 = createProject(name2);

    }
    @When("^Trying to change the (.*) name to (.*)$")
    public void trying_modify_project_name(String name1, String name2) {
        try {
           this.project2 = editProject(findByName(name1).getId(),name2);
        } catch (ProjectNameAlreadyTakenException pnat) {
           this.pnat = pnat;
        }

    }
    @Then("^Name cannot be modified$")
    public void name_is_not_changed(){
        assertNotNull(this.pnat);
        assertNotEquals(this.project1.getName(), this.project2.getName());
        deleteAll();
    }

    @Then("^Name is changed successfully to (.*)$")
    public void name_is_changed(String name){
        assertNull(this.pnat);
        assertEquals(name, this.project2.getName());
        deleteAll();
    }

    @Given("^A project named (.*) with estimated finish date (.*)$")

    public void project_with_finish_date(String name, String finishDate) {
        this.project1 = createProject(name, LocalDate.parse(finishDate));
    }

    @When("^Trying to change the finish date to (.*)$")
    public void trying_modify_project_finish_date(String finishDate) {
        try {
            this.project1 = editProject(this.project1.getId(), LocalDate.parse(finishDate), this.project1.getName());
        } catch (ProjectNameAlreadyTakenException pnat) {
            this.pnat = pnat;
        }

    }

    @Then("^Finish date is changed successfully to (.*)$")
    public void finish_date_is_changed(String finishDate){
        assertNull(this.pnat);
        assertEquals(LocalDate.parse(finishDate), this.project1.getFinishDate());
        deleteAll();
    }

    @Given("^A project named (.*) with description (.*)$")
    public void project_with_des(String name, String description) {
        this.project1 = createProject(name, description);
    }

    @When("^Trying to change the description to (.*)$")
    public void trying_modify_project_des(String description) {
        try {
            this.project1 = editProject(this.project1.getId(), description, this.project1.getName());
        } catch (ProjectNameAlreadyTakenException pnat) {
            this.pnat = pnat;
        }
    }

    @Then("^Description is changed successfully to (.*)$")
    public void description_is_changed(String description){
        assertNull(this.pnat);
        assertEquals(description, this.project1.getDescription());
        deleteAll();
    }
    @Given("^An existent project with name (.*)$")
    public void project_with_a_name(String name) {
       this.project1 = createProject(name);
    }

    @When("^Trying to change the status to (.*)$")
    public void trying_modify_project_status(String status) {
        try {
            this.project1 = editProject(this.project1.getId(), Status.valueOf(status), this.project1.getName());
        } catch (ProjectNameAlreadyTakenException pnat) {
            this.pnat = pnat;
        }
    }

    @Then("^Status is changed successfully to (.*)$")
    public void state_is_changed(String status){
        assertNull(this.pnat);
        assertEquals(Status.valueOf(status), this.project1.getStatus());
        deleteAll();
    }

    @After
    public void tearDown() {
        deleteAll();
        System.out.println("After all test execution");
    }

}
