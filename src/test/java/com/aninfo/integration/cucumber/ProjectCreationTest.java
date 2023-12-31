package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectCreationTest extends ProjectIntegrationServiceTest {
    private Project project1;
    private ProjectNameAlreadyTakenException pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @When("^Trying to create a project with name (.*)$")
    public void trying_create_project(String name) {
        try {
            this.project1 = createProject(name);
        } catch (ProjectNameAlreadyTakenException pnat) {
            this.pnat = pnat;
        }

    }

    @Then("^It is created successfully$")
    public void it_is_created_successfully() {
        assertNull(this.pnat);
        assertNotNull(this.project1);

    }
    @And("^Project is named (.*)$")
    public void project_is_named(String name) {
        assertEquals(name,this.project1.getName());
    }


    @Then("^It is not created because a project with that name already exists$")
    public void it_is_not_created_successfully() {
        assertNotNull(this.pnat);
        assertNull(this.project1);

    }

    @After
    public void beforeEachTest() {
        System.out.println("Resetting system");
        deleteAll();
    }

}