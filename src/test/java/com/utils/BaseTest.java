package com.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	private Response response;
	private static String authToken;

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
		response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
				.post(ConfigReader.getBaseUrl() + "/message/");
		return response;
	}

	// method to authenticate
	public static void authenticate() {

		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", ConfigReader.getUsername());
		credentials.put("password", ConfigReader.getPassword());

		Response authResponse = RestAssured.given().contentType("application/json").body(credentials)
				.post(ConfigReader.getBaseUrl() + "/auth/login");

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
		authToken = rawCookie.split("token=")[1].split(";")[0];
		System.out.println("Extracted Token: " + authToken);
	}

	// static method to return the unique token
	public static String getToken() {
		// if we don't have a token access
		if (authToken == null) {
			authenticate();
		}
		return authToken;
	}

}
