package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Project;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProjectCreationTest extends ProjectIntegrationServiceTest {

}