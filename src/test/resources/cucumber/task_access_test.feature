Feature: Accessing tasks in case they exist

  Scenario: No task in a project.
    Given A the project with the name project1
    When Trying to get the tasks
    Then I get nothing

  Scenario: Some tasks.
    Given A the project with the name project1 with 2 tasks
    When Trying to get the tasks
    Then I get two tasks

  Scenario: Searches for existent task
    Given A task in a project with name task1
    When Trying to find the task with name task1
    Then I get the task appropriately

  Scenario: Searches for non-existent task
    Given A task in a project with name task1
    When Trying to find the task with name task2
    Then I get an Invalid task Exception
