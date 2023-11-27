package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
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
public class ProjectAccessTest extends ProjectIntegrationServiceTest {

    private Project project;
    private Collection<Project> allProjects;
    private InvalidProjectException ipe;
    private ProjectNameAlreadyTakenException pnat;

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

    // probando que no haya ninguno
    @Given("^No projects$")
    public void no_project() {
    }

    @When("^Trying to get projects$")
    public void trying_to_get_projects() {
        this.allProjects = findAllProjects();
    }

    @Then("^I get no projects$")
    public void no_projects_gotten() {
        assertTrue(this.allProjects.isEmpty());
    }

    // probando que hayan 2
    @Given("^Two projects$")
    public void two_projects() {
        createProject("project1");
        createProject("project2");
        this.allProjects = findAllProjects();
    }

    @Then("^I get two projects$")
    public void two_projects_gotten() {
        assertEquals(this.allProjects.size(), 2);
    }

    // probando encontrarlo una vez creado
    @Given("^Project with name (.*)$")
    public void project_with_a_name(String name) {
        createProject(name);
    }

    @When("^Trying to find project with name (.*)$")
    public void trying_to_find_proj(String name) {
        try {
            this.project = findByName(name);
        } catch (InvalidProjectException ipe) {
            this.ipe = ipe;
        }
    }

    @Then("^I get the project appropriately$")
    public void obtains_project_appropriately() {
        assertNotNull(this.project);
        assertNull(this.ipe);
    }

    @Then("^I get an Invalid Project Exception$")
    public void obtains_invalid_project_exception() {
        assertNull(this.project);
        assertNotNull(this.ipe);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
