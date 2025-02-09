@regression
Feature: Message API Testing


  Background: a new message is available
  
    
  Scenario Outline: Create a new message 
    Given I have a valid message payload with '<messageid>' and '<name>' and '<email>' and '<phone>' and '<subject>' and '<description>'
    When I send a POST request to create a message 
    Then the response status should be 200
    
Examples:
    | messageid | name      		| email              	| phone       | subject      | description            |
    | 1         | Jean Dupont  	| jeandupont@yahoo.fr | 01234567890 | New booking  | New booking room for B an B    |
    | 2         | Lisa Dean  		| lisadean@gamil.com  | 09876543210 | Cancelling   | Cancelling reservation for next week    |

      



