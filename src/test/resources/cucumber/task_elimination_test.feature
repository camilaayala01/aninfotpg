Feature: Delete tasks
  Scenario: Can delete a existing task
    Given Only one task named TaskToDelete
    When Trying to delete the task
    Then Task TaskToDelete can no longer be found

  Scenario: Deleting one task when theres more than one
    Given A task named task2 and another named task3
    When Trying to delete task task2
    Then Task task2 can no longer be found
    And Task task3 can still be found