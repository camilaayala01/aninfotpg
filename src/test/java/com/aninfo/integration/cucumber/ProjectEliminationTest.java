package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.model.Project;
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
public class ProjectEliminationTest extends ProjectIntegrationServiceTest{
    private Long projectToDelete;
    private InvalidProjectException ipe;
    @Given("^Only one project named (.*)$")
    public void project_that_can_be_found(String name) {
        Project project = createProject(name);
        this.projectToDelete = project.getId();
        Project found = null;
        InvalidProjectException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidProjectException ipe) {
            exception = ipe;
        }

        assertNotNull(found);
        assertNull(exception);
        assertEquals(found.getName(), project.getName());
    }

    @When("^Trying to delete it$")
    public void trying_to_delete_project() {
        deleteById(this.projectToDelete);
    }

    @Given("^A project named (.*) and another named (.*)$")
    public void projects_that_can_be_found(String name1, String name2) {
        this.projectToDelete = createProject(name1).getId();
        createProject(name2);
    }

    @When("^Trying to delete project (.*)$")
    public void trying_to_delete_one_project(String name) {
        deleteById(findByName(name).getId());
    }

    @Then("^Project (.*) can no longer be found$")
    public void cannot_find_deleted_project(String name){
        Project found = null;
        InvalidProjectException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidProjectException ipe) {
            exception = ipe;
        }
        assertNull(found);
        assertNotNull(exception);
    }
    @And("^Project (.*) can still be found$")
    public void can_find_other_project(String name)
    {
        Project found = null;
        InvalidProjectException exception = null;
        try {
            found = findByName(name);
        } catch (InvalidProjectException ipe) {
            exception = ipe;
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
