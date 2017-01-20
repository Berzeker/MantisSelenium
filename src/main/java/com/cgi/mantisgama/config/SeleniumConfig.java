package com.cgi.mantisgama.config;

import org.openqa.selenium.WebDriver;

public class SeleniumConfig {

	private static SeleniumConfig INSTANCE;
	
	private WebDriver driver;
	private String pathToDriver;
	
	private SeleniumConfig(WebDriver webDriver) {
		this.driver = webDriver;
	}
	
	private SeleniumConfig(WebDriver webDriver, String pathToDriver) {
		this.driver = webDriver;
		this.pathToDriver = pathToDriver;
	}
	
	public static SeleniumConfig getInstance(WebDriver webDriver) {
		if (INSTANCE == null) {
			INSTANCE = new SeleniumConfig(webDriver);
		}
		return INSTANCE;
	}
	
	public static SeleniumConfig getInstance(WebDriver webDriver, String pathToDriver) {
		if (INSTANCE == null) {
			INSTANCE = new SeleniumConfig(webDriver, pathToDriver);
		}
		return INSTANCE;
	}
	

	public WebDriver getDriver() {
		return driver;
	}

	public String getPathToDriver() {
		return pathToDriver;
	}
	
	
}
