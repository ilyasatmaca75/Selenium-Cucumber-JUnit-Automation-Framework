@ui
Feature: Demonstration login
  A small local application demonstrates BDD structure without an external test dependency.

  @smoke
  Scenario: Successful login
    Given I open the demo login page
    When I sign in with username "demo" and password "secret123"
    Then the login message should be "Welcome, demo!"

  Scenario Outline: Invalid credentials
    Given I open the demo login page
    When I sign in with username "<username>" and password "<password>"
    Then the login message should be "Invalid credentials"

    Examples:
      | username | password  |
      | demo     | incorrect |
      | unknown  | secret123 |
