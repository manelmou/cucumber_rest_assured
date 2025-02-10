package com.stepsDefinitions;

import org.junit.Assert;

import com.utils.BaseTest;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMessage extends BaseTest {

	private Response deleteResponse;
	private Response responseCreate;
	private static final String BASE_URL = "https://automationintesting.online";
	private String messageId;
	private String token;

	@Before
	public void setUp() {
		token = getToken();
	}

	@Given("I have a message ID to delete")
	public void i_have_a_message_id_to_delete() {
		responseCreate = createMessage();
		messageId = responseCreate.jsonPath().getString("messageid");
		System.out.println(messageId);
	}

	@When("I send a DELETE request to remove the message")
	public void i_send_a_delete_request_to_remove_the_message() {

		// Use token in DELETE request
		deleteResponse = RestAssured.given().cookie("token", token).when().delete(BASE_URL + "/message/" + messageId);

		// Check if deleteResponse is valid
		if (deleteResponse == null) {
			System.out.println("Error: deleteResponse is null! DELETE request failed.");
			return;
		}

	}

	@Then("the response status should be 202")
	public void the_response_status_should_be_202() {
		Assert.assertEquals("Expected status code 202", 202, deleteResponse.getStatusCode());
	}

	@Then("the message should no longer exist")
	public void the_message_should_no_longer_exist() {
		Response getResponse = RestAssured.given().when().get(BASE_URL + "/message/" + messageId).then().log().all()
				.extract().response();
		Assert.assertEquals("Expected status code 500 after deletion", 500, getResponse.getStatusCode());
	}
}
