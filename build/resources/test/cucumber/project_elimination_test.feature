Feature: Delete projects
  Scenario: Can delete a existing project
    Given Only one project named ProjectToDelete
    When Trying to delete it
    Then Project ProjectToDelete can no longer be found

  Scenario: Deleting one project when theres more than one
    Given A project named ProjectRunway and another named ProjectX
    When Trying to delete project ProjectRunway
    Then Project ProjectRunway can no longer be found
    And Project ProjectX can still be found