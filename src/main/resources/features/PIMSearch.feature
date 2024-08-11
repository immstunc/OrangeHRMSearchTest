Feature: PIM Employee Search

  As a user of OrangeHRM
  I want to search for an employee by name in the PIM section
  So that the search functionality is verified

  Scenario: Search for an employee by name and verify results
    Given the user is logged in and on the PIM page
    When the user enters "Amanda" in the Employee Name field
    And the user clicks the Search button
    Then the user closes the browser tab
