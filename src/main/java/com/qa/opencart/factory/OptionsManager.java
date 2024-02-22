package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties pr;

	private ChromeOptions co;

	private FirefoxOptions fo;

	private EdgeOptions eo;

	public OptionsManager(Properties pr) {

		this.pr = pr;

	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();

		if (pr.getProperty("headless").trim().equals("true")) {

			co.addArguments("--headless");

		}

		if (pr.getProperty("incognito").trim().equals("true")) {

			co.addArguments("--incognito");

		}

		if (Boolean.parseBoolean(pr.getProperty("remote"))) {
			// Setting capabilities for running test cases on selenium GRID
			co.setCapability("browserName", "chrome");

			// Setting capabilities for running test cases on SELENOID GRID
			co.setBrowserVersion(pr.getProperty("browserversion").trim());//setting the browserversion after fetching it from prop files

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", pr.getProperty("testname"));
			co.setCapability("selenoid:options", selenoidOptions);
			
		}

		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();

		if (pr.getProperty("headless").trim().equals("true")) {

			fo.addArguments("--headless");

		}

		if (pr.getProperty("incognito").trim().equals("true")) {

			fo.addArguments("--incognito");

		}
		if (Boolean.parseBoolean(pr.getProperty("remote"))) {
			// for running test cases on selenium GRID
			fo.setCapability("browserName", "firefox");
			
			
			// Setting capabilities for running test cases on SELENOID GRID
			fo.setBrowserVersion(pr.getProperty("browserversion").trim());//setting the browserversion after fetching it from prop files

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", pr.getProperty("testname"));
			fo.setCapability("selenoid:options", selenoidOptions);
				
			
		}

		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();

		if (pr.getProperty("headless").trim().equals("true")) {

			eo.addArguments("--headless");

		}

		if (pr.getProperty("incognito").trim().equals("true")) {

			eo.addArguments("--inPrivate");

		}

//		if(Boolean.parseBoolean(pr.getProperty("remote")))
//		{
//			//for running test cases on selenium GRID
//			eo.setCapability("browserName", "edge");
//		}

		return eo;
	}
}
