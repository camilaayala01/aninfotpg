Feature: Finding existing projects
  Scenario: Can find an existing project
    Given A project
    When Searching this project by it's id
    Then It is found successfully
    And The project's id matches