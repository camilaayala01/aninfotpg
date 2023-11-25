package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;
    private String name;
    private InvalidProjectException ipe;
    private ProjectNameAlreadyTakenException pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    // probando encontrarlo una vez creado
    @Given("^Project with name(\\s+)$")
    public void project_with_a_name(String name)  {
        this.name = name;
        createProject(name);
    }

    @When("^Trying to find project with name (\\s+)$")
    public void trying_to_find_proj(String name) {
            try {
                this.project = findByName(this.name);
            } catch (InvalidProjectException ipe) {
                this.ipe = ipe;
            }
    }

    @Then("^I get the project appropriately$")
    public void obtains_project_appropriately() {
        assertNotNull(this.project);
        assertNull(this.ipe);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
