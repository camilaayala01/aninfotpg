package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
    @When("^Trying to change the project name to one that already exists$")
    public void trying_modify_project_name() {
        try {
           this.project2 = editProject(project2.getId(),project1.getName());
        } catch (ProjectNameAlreadyTakenException pnat) {
           this.pnat = pnat;
        }

    }
    @Then("^Name cannot be modified$")
    public void name_is_not_changed(){
        assertNotNull(this.pnat);
        assertNotEquals(this.project1.getName(),this.project2.getName());
    }


    /**
    -Modificar proyecto
       -> Si queres modicar el nombre (listo)
       -> Si queres modificar la fecha de finalizacion
       -> Si queres modificar la descripcion
       -> Si queres modificar el estado

     **/

}
