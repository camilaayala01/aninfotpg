package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectSearchTest  extends ProjectIntegrationServiceTest {
    private Long projectId;
    private Project project;
    private InvalidProjectException ipe;

    @Given("^A project$")
    public void project() {
        Project project = createProject();
        this.projectId = project.getId();
    }

    @When("^Searching this project by it's id$")
    public void trying_create_project() {
        try {
            this.project = findById(this.projectId);
        } catch (InvalidProjectException ipe) {
            this.ipe = ipe;
        }

    }
    @Then("^It is found successfully$")
    public void it_is_created_successfully() {
        assertNull(this.ipe);
        assertNotNull(this.project);
    }
    @And("^The project's id matches$")
    public void project_id_matches() {
        assertEquals(this.projectId,this.project.getId());
    }




}
