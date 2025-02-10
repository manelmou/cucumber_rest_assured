@suitedetestgestionmessage
Feature: Message API Testing

Background: messages are available


Scenario Outline: Get all messages
	When I send a GET request to fetch all messages
	Then the response status should be 200
	And the response should contain a list of messages

