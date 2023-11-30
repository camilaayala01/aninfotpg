Feature: Modify project fields
  Scenario: Can change the name of a project to a name that's not taken
    Given Two projects with names ProjectNoah and ProjectCami
    When Trying to change the ProjectCami name to ProjectDenise
    Then Name is changed successfully to ProjectDenise

  Scenario: Cannot change the name of a project to a name already taken
    Given Two projects with names ProjectNoah and ProjectCami
    When Trying to change the ProjectCami name to ProjectNoah
    Then Name cannot be modified

  Scenario: Can change the description of a project to another description
    Given A project named DescriptiveProject with description This is my Description
    When Trying to change the description to Another description
    Then Description is changed successfully to Another description

  Scenario: Can change the finish date of a project to another
    Given A project named ProjectWithFinishDate with estimated finish date 2007-12-03
    When Trying to change the finish date to 2018-12-03
    Then Finish date is changed successfully to 2018-12-03

  Scenario: Can change the status of a project
    Given An existent project with name ProjectWithAStatus
    When Trying to change the status to IN_PROGRESS
    Then Status is changed successfully to IN_PROGRESS
