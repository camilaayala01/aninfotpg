package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.model.Project;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectEliminationTest extends ProjectIntegrationServiceTest{
    private Long projectId;
    private InvalidProjectException ipe;
    @Given("^A project that can be found by it's id$")
    public void project_that_can_be_found() {
        Project project = createProject();
        this.projectId = project.getId();
        Project found = null;
        InvalidProjectException exception = null;
        try {
            found = findById(this.projectId);
        } catch (InvalidProjectException ipe) {
            exception = ipe;
        }

        assertNotNull(found);
        assertNull(exception);
        assertEquals(found.getId(), project.getId());
    }
    @When("^Trying to delete it$")
    public void trying_to_delete_project() {
        deleteById(this.projectId);
    }

    @Then("^It can no longer be found$")
    public void cannot_find_deleted_project(){
        Project found = null;
        InvalidProjectException exception = null;
        try {
            found = findById(this.projectId);
        } catch (InvalidProjectException ipe) {
            exception = ipe;
        }

        assertNull(found);
        assertNotNull(exception);

    }
}
