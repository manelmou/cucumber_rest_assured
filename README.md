Message API Test Cases

1-Create a Valid Message
**Scenario:
- Send a POST request with a valid message payload.
- Verify that the response status code is 201(Created)
- Ensure the message is stored successfully.

2- Create an Invalid Message
**Scenario:
- Send a POST request with missing or invalid fields.
- Verify that the response status code is 400 (Bad Request)
- Ensure proper error messages are returned.

3- Retrieve a Message
**Scenario:
- Send a GET request to retrieve an existing message.
- Verify that the response status code is 200(OK)
- Validate the response body contains the correct message details.

3- Retrieve all Messages
**Scenario:
- Send a GET request to retrieve all messages
- Verify that the response status code is 200(OK)

4-Delete a Message
**Scenario:
- Send a DELETE request to remove a message.
- Verify that the response status code is 200 (OK) if successful.
- Ensure the message is no longer available by sending a GET request, expecting a 500

5- Count Messages
**Scenario:
- Send a GET request to retrieve the number of messages.
- Verify that the response status code is 200 (OK).
- Validate the response contains a correct result.

6-Read a Message
**Scenario:
- Send a PUT request to read an existing message
- Verify that the response status code is 200(OK)
- Validate the response body contains the correct message details.


---------------------------
 How to Run the Tests
1. Ensure Java, Maven are installed.
2. Configure the config.properties file with the correct base URL and credentials.
3. Run the tests using: mvn test
4. To consult the test report, an HTML Cucumber Report is available in target>cucumber-reports>cucumber.html can be opened with your favorite browser