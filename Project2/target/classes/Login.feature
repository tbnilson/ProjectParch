Feature: User Login

	Background: 
	Given : The User is on the Parch Home Page

  Scenario Outline: User tries to Login
    Given : The User types in "<username>" and "<password>" in login field
    When : The User "<action>"
    Then : The User "<status>" logs in

    Examples: 
      | username  | password | action | status |
      | Admin | admin | clicks login | successfully |
      | name2 | test | presses enter | unsuccessfully |
