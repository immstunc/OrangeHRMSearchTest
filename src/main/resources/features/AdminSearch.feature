Feature: Admin User Search

  As a user of OrangeHRM
  I want to be able to search for a username in the Admin section
  So that the functionality of searching for users can be verified

  Scenario: Search for a username and verify results
    Given the user is logged in and on the Admin page
    When the user enters "Admin" in the Username field
    And the user clicks the Search button
    Then the user closes the browser tab

