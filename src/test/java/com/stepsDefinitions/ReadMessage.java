package com.stepsDefinitions;

import org.junit.Assert;

import com.utils.BaseTest;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReadMessage extends BaseTest {
	private Response readResponse;
	private Response responseCreate;
	private String messageId;

	@Given("I have a valid message ID to read")
	public void i_have_a_message_id_to_delete() {
		responseCreate = createMessage();
		messageId = responseCreate.jsonPath().getString("messageid");
		System.out.println(messageId);
	}

	@When("I send a PUT request to read the message")
	public void i_send_a_put_request_to_read_the_message() {

		readResponse = RestAssured.given().cookie("token", getToken()).when()
				.put(ConfigReader.getBaseUrl() + "/message/" + messageId + "/read");
		readResponse.print();

	}

	@Then("the response status should be correct")
	public void the_response_status_should_be_202() {
		Assert.assertEquals("Expected status code 202", 202, readResponse.getStatusCode());
	}

}
