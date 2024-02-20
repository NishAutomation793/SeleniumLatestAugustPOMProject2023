package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties pr;

	OptionsManager optionManager;

//This is thread local driver which is used if multiple thread attack the single test case then every thread will get its thread local driver	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver intDriver(Properties pr) {

		String browserName = pr.getProperty("browser"); // This is fetching value of browser from Properties file.
//String browserName = System.getProperty("browser");	 --> If I want to fetch the browser value from maven command	

		optionManager = new OptionsManager(pr);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":

			if (Boolean.parseBoolean(pr.getProperty("remote")))

			{
				// If we have remote as true inside properties file then our test cases will run
				// on selenium grid

				initRemoteDriver(browserName);

			} 
			else {
				// running the cases on local system
				// driver = new ChromeDriver(optionManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}
			break;

		case "firefox":

			if (Boolean.parseBoolean(pr.getProperty("remote")))

			{
				// If we have remote as true inside properties file then our test cases will run
				// on selenium grid

				initRemoteDriver(browserName);

			} else

			{
				// driver = new FirefoxDriver(optionManager.getFirefoxOptions());

				tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			}
			break;

		case "edge":

			if (Boolean.parseBoolean(pr.getProperty("remote")))

			{
				// If we have remote as true inside properties file then our test cases will run
				// on selenium grid

				initRemoteDriver(browserName);

			} else

				// driver = new EdgeDriver();

			{
				tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			}
				break;
		
		 default:
			System.out.println("Please enter correct browser");
			throw new FrameworkException("NO Browser Found...");

		}

		getthreadLocalDriver().manage().window().maximize();
		getthreadLocalDriver().manage().deleteAllCookies();
		getthreadLocalDriver().get(pr.getProperty("url"));

		return getthreadLocalDriver();
	}

	public static WebDriver getthreadLocalDriver() {

		return tlDriver.get();

	}

	public Properties initProp() {
		pr = new Properties();
		FileInputStream fp = null;

		// Here the below line is used to fetch the value of the
		// key that we give via cmd terminal with help of maven command
		// mvn clean install -Denv="qa", Here env is the key and qa is the value

		String envName = System.getProperty("env"); // This class is responsible for reading the env variable from the
													// maven command

		try {
			if (envName == null) {

				System.out.println("No Environment Entered..Running cases on defalut env");
				fp = new FileInputStream("./src/test/resource/config/config.properties");

			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":

					System.out.println("Environment Entered is: " + envName);

					fp = new FileInputStream("./src/test/resource/config/config.qa.properties");
					break;
				case "dev":
					System.out.println("Environment Entered is: " + envName);

					fp = new FileInputStream("./src/test/resource/config/config.dev.properties");
					break;
				case "uat":
					System.out.println("Environment Entered is: " + envName);

					fp = new FileInputStream("./src/test/resource/config/config.uat.properties");
					break;
				case "stage":
					System.out.println("Environment Entered is: " + envName);

					fp = new FileInputStream("./src/test/resource/config/config.stage.properties");
					break;

				default:
					System.out.println("Please enter correct env Name");

					throw new FrameworkException("WRONG ENVIRONMENT.." + envName);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			pr.load(fp); // Loading properties file

		} catch (IOException e) {
			e.printStackTrace();
		}
		return pr;
	}

	/**
	 * Take Screenshot Method
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getScreenshot(String methodName) {

		File src = ((TakesScreenshot) getthreadLocalDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * Running the test cases on Selenium Grid - Remote WebDriver
	 * 
	 * @param browserName
	 */
	private void initRemoteDriver(String browserName) {

		try {
			System.out.println("Running test cases on Selenium GRID with browser: " + browserName);

			switch (browserName.trim().toLowerCase()) {
			case "chrome":


				tlDriver.set(new RemoteWebDriver(new URL(pr.getProperty("hubUrl")), optionManager.getChromeOptions()));

				 break;
				 
			case "firefox":
				
				tlDriver.set(
						new RemoteWebDriver(new URL(pr.getProperty("hubUrl")), optionManager.getFirefoxOptions()));

				break;
				
			case "edge":

				tlDriver.set(new RemoteWebDriver(new URL(pr.getProperty("hubUrl")), optionManager.getEdgeOptions()));

				break;

			default:

				System.out.println("Wrong browser Name...Can't run test cases on Selenium Grid");
				break;
			}

		}

		catch (MalformedURLException e) {

		}
	}
}
