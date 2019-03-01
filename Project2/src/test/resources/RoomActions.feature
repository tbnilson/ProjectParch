Feature: User creates a new room invites another user to a room

  Background: 
    Given : The User is on the Parch Home Page

  Scenario Outline: User creates a new room
    Given : The User logs in as "<username>", "<password>"
    When : The user creates a room called "<roomname>"
    Then : "<roomname>" is "<status>" created

    Examples: 
      | username | password | status         | roomname |
      | test2    | testpass | succesfully    | testroom |
      | name2    |        7 | unsuccessfully | <>asdad  |

  Scenario Outline: User invites another user to a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user invites user "<invitee>"
    Then : "<invitee>" is "<status>" invited to "<roomname>"

    Examples: 
      | username | password | roomname | invitee | status       |
      | admin    | admin    | testroom | name2   | successfully |

  Scenario Outline: User views a room
    Given : The User logs in as "<username>", "<password>"
    When : The user selects the room "<roomname>"
    Then : "<roomname>" is "<status>" displayed

    Examples: 
      | username | password | status         | roomname |
      | admin    | admin    | succesfully    | testroom |
      | name2    |        7 | unsuccessfully | <>asdad  |

  Scenario Outline: User posts a message to a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    Given : The user types "<message>" in room
    When : The user "<action>"
    Then : The message is "<status>" displayed

    Examples: 
      | username | password | status      | roomname | message |
      | admin    | admin    | succesfully | testroom | "Hi"    |

  Scenario Outline: User deletes a message
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to delete "<messageID>"
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

  Scenario Outline: User bans another user from a room
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to ban "<banneduser>" from the room
    Then : The user "<banneduser>" is "<status>" banned

    Examples: 
      | username | password | status      | roomname | banneduser |
      | admin    | admin    | succesfully | testroom | name2      |

  Scenario Outline: User deletes a message
    Given : The User logs in as "<username>", "<password>"
    Given : The user selects the room "<roomname>"
    When : The user attempts to delete "<roomname>"
    Then : The room "<roomname>" is "<status>" deleted

    Examples: 
      | username | password | status      | roomname |
      | admin    | admin    | succesfully | testroom |
