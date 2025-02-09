package com.stepsDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMessage {

	private Response deleteResponse;
	private static final String BASE_URL = "https://automationintesting.online";
	private int messageId;

	@Given("I have a message ID to delete")
	public void i_have_a_message_id_to_delete() {
		messageId = 1;
	}

	@When("I send a DELETE request to remove the message")
	public void i_send_a_delete_request_to_remove_the_message() {

		// Get the token
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "admin");
		credentials.put("password", "password");

		Response authResponse = RestAssured.given().contentType("application/json").body(credentials)
				.post(BASE_URL + "/auth/login");

		// Check if authResponse is valid
		if (authResponse == null) {
			System.out.println("Error: authResponse is null! API request failed.");
			return;
		}
		// Extract token from SetCookie header
		String rawCookie = authResponse.getHeader("Set-Cookie");
		if (rawCookie == null || !rawCookie.contains("token=")) {
			System.out.println("Error: No token found in response cookies!");
			return;
		}

		// Extract token from cookie
		String token = rawCookie.split("token=")[1].split(";")[0];
		System.out.println("Extracted Token: " + token);

		// Use token in DELETE request
		Response deleteResponse = RestAssured.given().cookie("token", token).when()
				.delete(BASE_URL + "/message/" + messageId);

		// Check if deleteResponse is valid
		if (deleteResponse == null) {
			System.out.println("Error: deleteResponse is null! DELETE request failed.");
			return;
		}
		System.out.println("Delete Status Code: " + deleteResponse.getStatusCode());
		System.out.println("Delete Response: " + deleteResponse.getBody().asString());
	}

	@Then("the response status should be 202")
	public void the_response_status_should_be_202() {
		Assert.assertEquals("Expected status code 202", 202, deleteResponse.getStatusCode());
	}

	@Then("the message should no longer exist")
	public void the_message_should_no_longer_exist() {
		Response getResponse = RestAssured.given().when().get(BASE_URL + messageId).then().log().all().extract()
				.response();
		Assert.assertEquals("Expected status code 404 after deletion", 404, getResponse.getStatusCode());
	}
}
