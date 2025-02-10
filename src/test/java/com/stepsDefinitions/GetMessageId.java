package com.stepsDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import com.utils.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMessageId extends BaseTest {
	private Response response;
	private static final String BASE_URL = "https://automationintesting.online/message/";
	private String messageId;
	private Response responseCreate;
	JSONObject body = new JSONObject();

	@Given("I have a valid message ID")
	public void i_have_a_valid_message_id() {
		responseCreate = createMessage();
		messageId = responseCreate.jsonPath().getString("messageid");
		System.out.println(messageId);
	}

	@When("I send a GET request to retrieve the message")
	public void i_send_a_get_request_to_retrieve_the_message() {
		response = RestAssured.given().when().get(BASE_URL + messageId).then().log().all().extract().response();
	}

	@Then("the response should contain the correct message details")
	public void the_response_should_contain_the_correct_message_details() {
		Assert.assertEquals("Expected status code 200", 200, response.getStatusCode());
		Assert.assertEquals("Expected correct message ID", messageId, response.jsonPath().getString("messageid"));
		Assert.assertNotNull("Expected name field to be present", response.jsonPath().getString("name"));
		Assert.assertNotNull("Expected email field to be present", response.jsonPath().getString("email"));
		Assert.assertNotNull("Expected phone field to be present", response.jsonPath().getString("phone"));
		Assert.assertNotNull("Expected subject field to be present", response.jsonPath().getString("subject"));
		Assert.assertNotNull("Expected description field to be present", response.jsonPath().getString("description"));

	}

}
