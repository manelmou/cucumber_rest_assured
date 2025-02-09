package com.stepsDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MessageControler {

	private Response response;
	private static final String BASE_URL = "https://automationintesting.online/message/";
	JSONObject body = new JSONObject();

	@Given("I have a valid message payload with {string} and {string} and {string} and {string} and {string} and {string}")
	public void i_have_a_valid_message_payload(String messageid, String name, String email, String phone,
			String subject, String description) {
		body.put("messageid", messageid);
		body.put("name", name);
		body.put("email", email);
		body.put("phone", phone);
		body.put("subject", subject);
		body.put("description", description);
		System.out.println(body.toString());

	}

	@When("I send a POST request to create a message")
	public void i_send_a_post_request_to_create_a_message() {
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(BASE_URL);
		response.print();
		System.out.println("Response: " + response.getBody().asString());
	}

	@Then("the response status should be 200")
	public void the_response_status_should_be_200() {
		Assert.assertEquals("Expected status code 200", 200, response.getStatusCode());
	}

}
