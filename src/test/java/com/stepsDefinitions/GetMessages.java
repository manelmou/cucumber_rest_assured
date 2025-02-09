package com.stepsDefinitions;

import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMessages {

	private Response response;
	private static final String BASE_URL = "https://automationintesting.online/message/";
	JSONObject body = new JSONObject();

	@When("I send a GET request to fetch all messages")
	public void i_send_a_get_request_to_fetch_all_messages() {
		response = RestAssured.given().when().get(BASE_URL);
		response.print();
	}

	@Then("the response status should be 200")
	public void the_response_status_should_be_200() {
		Assert.assertEquals("Expected status code 200", 200, response.getStatusCode());
	}

	@Then("the response should contain a list of messages")
	public void the_response_should_contain_a_list_of_messages() {
		Assert.assertNotNull("Response is null!", response);
		List<?> messages = response.jsonPath().getList("messages");
		Assert.assertFalse("The list of messages is empty!", messages.isEmpty());
	}

}
