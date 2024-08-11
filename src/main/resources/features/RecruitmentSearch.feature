Feature: Recruitment Search Functionality
  As a user of OrangeHRM
  I want to be able to search for candidate name
  So that I can verify that the search functionality works correctly

  Background:
    Given I am on the OrangeHRM login page
    And I log in with valid credentials

  Scenario: Search for candidate
    When I navigate to the Recruitment page
    And I select write candidate name as "John Doe"
    Then I click the Search button
