Feature: Making, editing, and deleting posts

  Background: 
    Given : The User is on the Parch Home Page

  Scenario Outline: User posts a message to a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    Given : The user types "<message>" in room
    When : The User "clicks create new post"
    Then : The message is "<status>" displayed

    Examples: 
      | username | password | status      | roomname  | message               |
      | test21   | testpass | succesfully | testroom2 | Hi from Cucumber Test |

  Scenario Outline: User deletes a message
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to delete most recent message
    Then : The message "<messageID>" is "<status>" deleted

    Examples: 
      | username | password | status      | roomname | messageID |
      | admin    | admin    | succesfully | testroom |        12 |

  Scenario Outline: User edits a message
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to edit "<messageID>" with new text "<newmessage>"
    Then : The message "<messageID>" is "<status>" edited.

    Examples: 
      | username | password | status      | roomname | messageID | newmessage |
      | admin    | admin    | succesfully | testroom |        12 | "Hi!"      |

  Scenario Outline: User deletes a message
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to delete "<roomname>"
    Then : The room "<roomname>" is "<status>" deleted

    Examples: 
      | username | password | status      | roomname |
      | admin    | admin    | succesfully | testroom |
