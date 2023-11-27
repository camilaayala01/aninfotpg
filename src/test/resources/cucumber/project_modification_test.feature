Feature: Modify project fields
  Scenario: Cannot change the name of a project to a name already taken
    Given Two projects with names name1 and name2
    When Trying to change the project name to one that already exists
    Then Name cannot be modified