package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Account;
import com.aninfo.model.Project;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;
    private String name;
    private InvalidProjectException ipe;
    private ProjectNameAlreadyTakenException pnat;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    // probando encontrarlo
    @Given("^Project with name(\\s+)$")
    public void project_with_a_name(String name)  {
        this.name = name;
        project = createProject(name);
    }

    @When("^Trying to find project with name (\\s+)$")
    public void trying_to_find_proj(String name) {
            try {
                this.project = findByName(this.name);
            } catch (InvalidProjectException ipe) {
                this.ipe = ipe;
            }
    }

    @Then("^Account balance should be (\\d+)$")
    public void account_balance_should_be(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }

    @Then("^Operation should be denied due to insufficient funds$")
    public void operation_should_be_denied_due_to_insufficient_funds() {
        assertNotNull(ife);
    }

    @Then("^Operation should be denied due to negative sum$")
    public void operation_should_be_denied_due_to_negative_sum() {
        assertNotNull(dnse);
    }

    @And("^Account balance should remain (\\d+)$")
    public void account_balance_should_remain(int balance) {
        assertEquals(Double.valueOf(balance), account.getBalance());
    }



    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
