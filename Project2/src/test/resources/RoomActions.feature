
Feature: User creates a new room invites another user to a room

	Background:
  	Given : The User is on the Parch Home Page
  	
  Scenario Outline: User creates a new room
    Given : The User types in "<username>" and "<password>" in login field
    Given : The User "presses enter"
    When : The user creates a room called "<roomname>"
    Then : "<roomname>" is "<status>" created

    Examples: 
      | username  | password | status  | roomname |
      | admin | admin | succesfully | testroom |
      | name2 |     7 | unsuccessfully | <>asdad |
      
   
