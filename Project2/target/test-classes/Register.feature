
Feature: User Registers new account
 
 Background: 
 Given : The User is on the Parch Home Page
 
  Scenario Outline: User tries to register a new account
    Given : The User types in "<username>" and "<password>" and "<email>" in register field
    When : The User "<action>"
    Then : The User "<status>" registers

    Examples:  
      | username  | password | action | status | email |
      | Admin | admin | clicks register | unsuccessfully | 1231231 |
      | name2 | test | presses enter | successfully | asdadsad@gmail.com |
