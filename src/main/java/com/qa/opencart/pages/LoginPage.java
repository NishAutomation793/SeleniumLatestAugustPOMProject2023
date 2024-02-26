package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

/**
 * Here we will maintain private WebDriver instance and private locators so that
 * not anyone can access these things and manipulate them
 * 
 * This class also contains the implementation of methods This is the actual
 * Page Object Model design pattern that we are using with private data members
 * having the public methods to access which is called encapsulation
 * 
 * All the Page classes will be using the Element Util class to access all the
 * methods we created
 */
public class LoginPage {

	private WebDriver driver;

	private ElementUtil ut;

	// Maintaining Private Page locators:

	private By useremail = By.id("input-email");
	private By pwd = By.id("input-password");

	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");

	private By logo = By.tagName("img");

	private By searchBox = By.cssSelector("input[name='search']");

	private By registerLink = By.linkText("Register");

	private By footerLinks = By.xpath("//div[@class='col-sm-3']//a");

	private By warningAlertsMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	private static final Logger log=LogManager.getLogger(LoginPage.class);

	
	// Page Constructor:
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(driver);
	}

	// Page Class Actions

	public boolean isLogoVisible() {

		return driver.findElement(logo).isDisplayed();

	}

	public String getLoginPageTitle() {

		String title = ut.waitTillTitleVisible(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		//System.out.println("The Title of the Page is :" + title);
		
		log.info("The Title of the Page is :" + title);
		return title;
	}

	public String getLoginPageURL() {

		String currentURL = ut.waitforURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION,
				AppConstants.SHORT_DEFAULT_WAIT);

		//System.out.println("The URL of the Page is :" + currentURL);
		
		log.info("The URL of the Page is :" + currentURL);
		return currentURL;
	}

	public boolean isForgetPwdLinkAvailale() {
		return ut.waitVisibilityofElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();

	}

	public AccountsPage doLogin(String userName, String password) {

		ut.waitVisibilityofElement(useremail, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(userName);
		ut.waitVisibilityofElement(pwd, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(password);

		ut.doClickWithWait(loginBtn, AppConstants.SHORT_DEFAULT_WAIT);
// We have to return the object of next landing AccountPage so to access the new Page after
// login
		return new AccountsPage(driver);

	}

	public String doLoginWrongCredentials(String wrongName, String wrongPass) {

		ut.waitPresenceofElement(useremail, 2).clear();
		ut.waitPresenceofElement(pwd, 2).clear();

		
		ut.waitVisibilityofElement(useremail, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(wrongName);
		ut.waitVisibilityofElement(pwd, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(wrongPass);

		ut.doClickWithWait(loginBtn, AppConstants.SHORT_DEFAULT_WAIT);
		
		String message=ut.doElementGetText(warningAlertsMessage);
		//System.out.println("Warning Message is: "+message);
		
		log.info("Warning Message is: "+message);
		return message;

	}

	public String searchBoxAvailable() {
		if (ut.waitPresenceofElement(searchBox, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed()) {

			String placeHolder = ut.getElementAttribute(searchBox, "placeholder");

			//System.out.println("Placeholder for Search Box is: " + placeHolder);

			log.info("Placeholder for Search Box is: " + placeHolder);
			return placeHolder;
		}

		return null;
	}

	public List<String> footerLinksAvailability() {

		List<String> footerLinksText = ut.getElementsTextList(footerLinks);

		//System.out.println("Footer Links are: " + footerLinksText);

		log.info("Footer Links are: " + footerLinksText);
		return footerLinksText;
	}

//Here we are implementing method of click Registering and returning object of RegisterPage class object	
	public RegisterPage naviagteToRegistrationPage() {
		if (ut.waitPresenceofElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed()) {
			ut.doClick(registerLink);

		}

		return new RegisterPage(driver);

	}
	
	public ForgetPwdPage navigateForgetPwdPage() {
		
		ut.doClickWithWait(forgotPwdLink,AppConstants.SHORT_DEFAULT_WAIT);
		
		return new ForgetPwdPage(driver);
	}
	
}
