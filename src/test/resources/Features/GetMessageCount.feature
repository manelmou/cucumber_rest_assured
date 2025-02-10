@suitedetestgestionmessage
Feature: Message API Testing

Background: messages are available


Scenario Outline: Get messages count
	When I send a GET request to fetch number of messages
	Then the response count status should be 200
	And the response should containthe number of messages
