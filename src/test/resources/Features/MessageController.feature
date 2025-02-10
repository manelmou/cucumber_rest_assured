@suitedetestgestionmessage
Feature: Message API Testing


  Background: a new message is available
  
    
  Scenario Outline: Create a new message 
    Given I have a message payload with '<messageid>' and '<name>' and '<email>' and '<phone>' and '<subject>' and '<description>'
    When I send a POST request to create a message 
    Then the response status should be '<status_code>'
    
Examples:
    | messageid | name      		| email              	| phone       | subject      | description           									| status_code |
    | 2         | Jean Dupont  	| jeandupont@yahoo.fr | 01234567890 | New booking  | New booking room for B an B   	 				| 201 				|
    | 3         | Lisa Dean  		| lisadean@gmail.com  | 09876543210 | Cancelling   | Cancelling reservation for next week   |	201					|
    | 4         | Jean Dupont  	| jeandupont@yahoo.fr | 01234567890 | New booking  |  																			|	400					|
    | 5         | Paul Dean  		| lisadean@gamil.com  | 000 				| Cancelling   | Cancelling reservation for next week   |	400					|
    | 6         | Lisa Dean  		| lisadean  					| 01234567890 | Cancelling   | Cancelling reservation for next week   |	400 				|
    | 7	        | 				  		| lisadean  					| 01234567890 | Cancelling   | Cancelling reservation for next week   |	400 				|

     



