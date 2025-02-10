package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();

	static {
		try {
			FileInputStream file = new FileInputStream("src/test/resources/Config/config.properties");
			properties.load(file);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file!", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getBaseUrl() {
		return getProperty("baseurl");
	}

	public static String getUsername() {
		return getProperty("username");
	}

	public static String getPassword() {
		return getProperty("password");
	}

}
