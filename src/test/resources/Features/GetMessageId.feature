@suitedetestgestionmessage
Feature: Message API Testing

Background: messages are available
 
 
Scenario Outline: Retrieve a specific message
    Given I have a valid message ID
    When I send a GET request to retrieve the message
   	Then the response should contain the correct message details
