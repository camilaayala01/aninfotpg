Feature: Project creation
  Checking different project creation alternatives

  Scenario: Successfully create a project
    Given No projects
    When Trying to create a project with name Project1
    Then It is created successfully
    And Project is named Project1


  Scenario: Cannot create a project with existing name
    Given Project with name Project2
    When Trying to create a project with name Project2
    Then It is not created because a project with that name already exists

  Scenario: Can create project with different names
    Given Project with name Project3
    When Trying to create a project with name Project4
    Then It is created successfully
    And Project is named Project4