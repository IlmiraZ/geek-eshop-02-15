Feature: Login

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login.html page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then any user logged in
    And click logout button

    Examples:
      | username | password | username |
      | admin | admin | admin |
