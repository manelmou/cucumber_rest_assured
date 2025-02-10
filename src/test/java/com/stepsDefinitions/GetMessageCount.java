package com.stepsDefinitions;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMessageCount {

	private Response response;
	private static final String BASE_URL = "https://automationintesting.online/message/count";
	JSONObject body = new JSONObject();

	@When("I send a GET request to fetch number of messages")
	public void i_send_a_get_request_to_fetch_number_of_messages() {
		response = RestAssured.given().when().get(BASE_URL);
		response.print();
	}

	@Then("the response count status should be 200")
	public void the_response_count_status_should_be_200() {
		Assert.assertEquals("Expected status code 200", 200, response.getStatusCode());
	}

	@And("the response should containthe number of messages")
	public void the_response_should_contain_a_number_of_messages() {
		String count = response.jsonPath().getString("count");
		Assert.assertNotNull("Number is null!", count);
		Assert.assertTrue("the number is incorrect", Integer.parseInt(count) >= 0);

	}
}
