package com.stepsDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MessageControler {

	private Response response;
	private static final String BASE_URL = ConfigReader.getBaseUrl() + "/message/";

	JSONObject body = new JSONObject();

	@Given("I have a message payload with {string} and {string} and {string} and {string} and {string} and {string}")
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
		response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(BASE_URL);
		response.print();
		System.out.println("Response: " + response.getBody().asString());
	}

	@Then("the response status should be {string}")
	public void the_response_status_should_be_201(String expectedStatusCode) {
		Assert.assertEquals("Expected status code not correct !", Integer.parseInt(expectedStatusCode),
				response.getStatusCode());
	}

}
