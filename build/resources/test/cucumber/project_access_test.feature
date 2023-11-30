Feature: Accessing projects in case they exist

  Scenario: No projects in system.
    Given No projects
    When Trying to get projects
    Then I get no projects

  Scenario: Some projects.
    Given Two projects
    When Trying to get projects
    Then I get two projects

  Scenario: Searches for existent project
    Given Project with name MyProject
    When Trying to find project with name MyProject
    Then I get the project appropriately

  Scenario: Searches for non-existent project
    Given Project with name MyProject1
    When Trying to find project with name MyProject2
    Then I get an Invalid Project Exception
