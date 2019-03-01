Feature: User Login

  Background: 
    Given : The User is on the Parch Home Page

  #Tests if user name and password match
  Scenario Outline: User tries to Login
    Given : The User types in "<username>" and "<password>" in login field
    When : The User "<action>"
    Then : The User "<username>" "<status>" logs in

    Examples: 
      | username | password | action        | status         |
      | test1    | testpass | clicks login  | successfully   |
      | name2    | test     | presses enter | unsuccessfully |

  #This scenario should test whether email and usernames are unique
  #Also test if bad passwords fail
  Scenario Outline: User tries to register a new account
    Given : The User types in "<username>" and "<password>" and "<email>" in register field
    When : The User "<action>"
    Then : The User "<username>" "<status>" registers

    Examples: 
      | username | password | action          | status         | email              |
      | Admin    | admin    | clicks register | unsuccessfully |            1231231 |
      | name2    | test     | clicks register | successfully   | asdadsad@gmail.com |
      | test2    | testpass | clicks register | unsuccessfully |            1231231 |
      | name2    | test     | clicks register | successfully   | asdadsad@gmail.com |
