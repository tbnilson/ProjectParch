Feature: User creates a new room invites another user to a room

  Background: 
    Given : The User is on the Parch Home Page

  Scenario Outline: User creates a new room
    Given : The User logs in as "<username>", "<password>"
    When : The user creates a room called "<roomname>"
    Then : "<roomname>" is "<status>" created

    Examples: 
      | username | password | status       | roomname  |
      | test21   | testpass | successfully | testroom2 |
      | test22   | testpass | successfully | <>asdad2  |

  Scenario Outline: User invites another user to a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user invites user "<invitee>"
    Then : "<invitee>" is "<status>" invited to "<roomname>"

    Examples: 
      | username | password | roomname | invitee | status       |
      | test22   | testpass | <>asdad  | test21  | successfully |

  Scenario Outline: User views a room
    Given : The User logs in as "<username>", "<password>"
    When : The user selects the room "<roomname>"
    Then : "<roomname>" is "<status>" displayed

    Examples: 
      | username | password | status         | roomname |
      | admin    | admin    | succesfully    | testroom |
      | name2    |        7 | unsuccessfully | <>asdad  |

  Scenario Outline: User bans another user from a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to ban "<banneduser>" from the room
    Then : The user "<banneduser>" is "<status>" banned

    Examples: 
      | username | password | status      | roomname | banneduser |
      | admin    | admin    | succesfully | testroom | name2      |
