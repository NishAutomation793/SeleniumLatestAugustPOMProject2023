package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties pr;

	private ChromeOptions co;

	private FirefoxOptions fo;

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

		return fo;
	}
}
