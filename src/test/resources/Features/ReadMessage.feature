@suitedetestgestionmessage
Feature: Message API Testing

Background: messages are available
 
 
Scenario Outline: Read a specific message
    Given I have a valid message ID to read
    When I send a PUT request to read the message
   	Then the response should contain the correct message to read
