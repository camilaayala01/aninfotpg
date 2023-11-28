Feature: Modify task info.
  Scenario: Can change the name of a task to a name that's not taken
    Given Two tasks with names TaskA and TaskB
    When Trying to modified the task TaskB name to TaskC
    Then Name task is changed successfully to TaskC

  Scenario: Cannot change the name of a task to a name already taken
    Given Two tasks with names TaskA and TaskB
    When Trying to modified the task TaskB name to TaskA
    Then Name task cannot be modified

  Scenario: Can change the description of a task to another description
    Given A task named DescriptiveTask with description This is a description example
    When Trying to modified the description to This is another description example
    Then Description is modified successfully to This is another description example

  Scenario: Can change the estimated finish date of a task to another
    Given A task named TaskE with estimated finish date 2007-12-03
    When Trying to change the estimated finish date to 2018-12-03
    Then Estimated finish date is changed successfully to 2018-12-03

  Scenario: Can change the status of a task
    Given An existent task with name TaskS
    When Trying to modify the status to IN_PROGRESS
    Then Status is modified successfully to IN_PROGRESS