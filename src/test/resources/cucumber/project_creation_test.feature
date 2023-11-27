Feature: Project creation
  Checking different project creation alternatives

  Scenario: Successfully create a project
    Given No projects
    When Trying to create a project with name project1
    Then It is created successfully
    And Project is named project1


  Scenario: Cannot create a project with existing name
    Given Project with name project1
    When Trying to create a project with name project1
    Then It is not created because a project with that name already exists


