package com.qa.opencart.factory;

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

		if(Boolean.parseBoolean(pr.getProperty("remote")))
		{
			//for running test cases on selenium GRID
			co.setCapability("browserName", "chrome");
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
		if(Boolean.parseBoolean(pr.getProperty("remote")))
		{
			//for running test cases on selenium GRID
			fo.setCapability("browserName", "firefox");
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
