Feature: Delete projects
  Scenario: Can delete a existing project
    Given A project that can be found by it's id
    When Trying to delete it
    Then It can no longer be found