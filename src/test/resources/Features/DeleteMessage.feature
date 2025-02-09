@suitedetestgestionmessage
Feature: Message API Testing

Background: messages are available
 
 
Scenario Outline: Delete a message 
    Given I have a message ID to delete
    When I send a DELETE request to remove the message
    Then the response status should be 202
    Then the message should no longer exist
