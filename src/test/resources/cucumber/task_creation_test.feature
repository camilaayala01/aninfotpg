Feature: Task creation

  Scenario: Successfully create a task
    Given No tasks
    When Try to create a task with name task1
    Then The task is created successfully
    And Task is named task1


  Scenario: Cannot create a task with existing name
    Given A task in a project with name task1
    When Try to create a task with name task1
    Then It is not created because a task with that name already exists

  Scenario: Successfully create a second task
    Given A task in a project with name task1
    When Try to create a task with name task2
    Then The task is created successfully
    And Task is named task2