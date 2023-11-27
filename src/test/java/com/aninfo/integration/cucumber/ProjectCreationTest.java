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
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectCreationTest extends ProjectIntegrationServiceTest {
    private Project project;
    private ProjectNameAlreadyTakenException pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Resetting system");
        deleteAll();
    }


    @When("^Trying to create a project with name (.*)$")
    public void trying_create_project(String name) {
        try {
            this.project = createProject(name);
        } catch (ProjectNameAlreadyTakenException pnat) {
            this.pnat = pnat;
        }

    }

    @Then("^It is created successfully$")
    public void it_is_created_successfully() {
        assertNull(this.pnat);
        assertNotNull(this.project);

    }
    @And("^Project is named (.*)$")
    public void project_is_named(String name) {
        assertEquals(name,this.project.getName());
    }


    @Then("^It is not created because a project with that name already exists$")
    public void it_is_not_created_successfully() {
        assertNotNull(this.pnat);
        assertNull(this.project);

    }




}