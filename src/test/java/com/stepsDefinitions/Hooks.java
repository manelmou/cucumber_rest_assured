package com.stepsDefinitions;

import com.utils.BaseTest;

import io.cucumber.java.Before;

public class Hooks {
	// to run first
	@Before(order = 0)
	public void setup() {
		// authenticate before tests to ensure token is present
		BaseTest.authenticate();

	}
}
