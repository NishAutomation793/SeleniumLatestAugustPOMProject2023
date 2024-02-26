package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;

	private ElementUtil ut;

	private By firstName = By.id("input-firstname");

	private By lastName = By.id("input-lastname");

	private By email = By.id("input-email");

	private By telephone = By.id("input-telephone");

	private By password = By.id("input-password");

	private By confPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");

	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");

	private By privacyPolicy = By.xpath("//input[@name='agree']");

	private By continueBtn = By.xpath("//input[@value='Continue']");

	private By userSuccessMessage = By.tagName("h1");
	
	private By logOutLink=By.linkText("Logout");
	
	private By registerLink = By.linkText("Register");
	
	private static final Logger log=LogManager.getLogger(RegisterPage.class);


	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	public String userRegistration(String firstname, String lastname, String emailId, String tel, String pass,String subscribe) {

		ut.waitPresenceofElement(firstName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(firstname);
		ut.doSendKeys(lastName, lastname);
		ut.doSendKeys(email, emailId);
		ut.doSendKeys(telephone, tel);
		ut.doSendKeys(password, pass);
		ut.doSendKeys(confPassword, pass);

		if (subscribe.equalsIgnoreCase("Yes")) {
			ut.doClick(subscribeNo);
		} else {
			ut.doClick(subscribeYes);
		}

		ut.doClickWithWait(privacyPolicy, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doClickWithWait(continueBtn,AppConstants.SHORT_DEFAULT_WAIT);

		String userSuccMessage = ut.waitPresenceofElement(userSuccessMessage, AppConstants.MEDIUM_DEFAULT_WAIT)
				.getText();
		
		//System.err.println("User Success Message is:  "+userSuccMessage);

		log.info("User Success Message is:  "+userSuccMessage);
		ut.doClickWithWait(logOutLink, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doClickWithWait(registerLink, AppConstants.SHORT_DEFAULT_WAIT);
		
		return userSuccMessage;
		
		
	}

}
