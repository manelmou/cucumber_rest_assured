package com.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	private Response response;
	private static final String BASE_URL = "https://automationintesting.online";

	protected Response createMessage() {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("messageid", 2);
		body.put("name", "Lisa Dean");
		body.put("email", "lisadean@yahoo.fr");
		body.put("phone", "01234567895");
		body.put("subject", "thank you for the nice room");
		body.put("description",
				"Thank you for the nice hotel and the nice room, we really enjoyed our time in your Bed and Breakfast");

		// Get response
		response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(BASE_URL + "/message/");
		return response;
	}

	protected String getToken() {

		// Get the token
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "admin");
		credentials.put("password", "password");

		Response authResponse = RestAssured.given().contentType("application/json").body(credentials)
				.post(BASE_URL + "/auth/login");

		// Check if authResponse is valid
		if (authResponse == null) {
			System.out.println("Error: authResponse is null! API request failed.");

		}
		// Extract token from SetCookie header
		String rawCookie = authResponse.getHeader("Set-Cookie");
		if (rawCookie == null || !rawCookie.contains("token=")) {
			System.out.println("Error: No token found in response cookies!");

		}

		// Extract token from cookie
		String token = rawCookie.split("token=")[1].split(";")[0];
		System.out.println("Extracted Token: " + token);
		return token;
	}

}
