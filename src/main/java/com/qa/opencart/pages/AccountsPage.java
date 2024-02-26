package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;

	private ElementUtil ut;
	
	private static final Logger log=LogManager.getLogger(AccountsPage.class);


	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	private By logOutLink = By.linkText("Logout");

	private By searchBox = By.cssSelector("input[name='search']");

	private By headersLinks = By.xpath("//ul[@class='nav navbar-nav']/li/a");

	private By headerText = By.tagName("h2");

	private By searchButton = By.xpath("//div[@id='search']//button");

	private By addressBookLink = By.linkText("Address Book");
	private By orderHistoryLink=By.linkText("Order History");


	public boolean checkLogoutLink() {

		return ut.waitVisibilityofElement(logOutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public void doLogout() {
		if (checkLogoutLink()) {
			ut.doClickWithWait(logOutLink, AppConstants.SHORT_DEFAULT_WAIT);
		}
	}

	public List<String> getHeadersText() {
		List<String> allHeaders = new ArrayList<String>();
		List<WebElement> totalHeaders = ut.waitVisibilityofElements(headerText, AppConstants.SHORT_DEFAULT_WAIT);

		for (WebElement h : totalHeaders) {

			String text = h.getText();
			allHeaders.add(text);
		}

		return allHeaders;
	}

	public List<String> getHeadersLinks() {
		List<String> HeadersLinks = new ArrayList<String>();
		List<WebElement> totalHeadersLinks = ut.waitVisibilityofElements(headersLinks, AppConstants.SHORT_DEFAULT_WAIT);

		for (WebElement h : totalHeadersLinks) {

			String text = h.getText();
			HeadersLinks.add(text);
		}

		return HeadersLinks;

	}

	public String getAccountPageTitle() {

		String title = ut.waitTillTitleVisible(AppConstants.ACC_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		//System.out.println("The Title of the Account Page is :" + title);
		
		log.info("The Title of the Account Page is :" + title);
		return title;
	}

	public String getAccountPageURL() {

		String currentURL = ut.waitforURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);

		//System.out.println("The URL of the Account Page is :" + currentURL);
		
		log.info("The URL of the Account Page is :" + currentURL);
		return currentURL;
	}

//Search Product on My  Accounts Page

	public SearchResultsPage searchProduct(String productName) {
		ut.waitPresenceofElement(searchBox, AppConstants.SHORT_DEFAULT_WAIT).clear();
		ut.waitPresenceofElement(searchBox, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(productName);
		ut.doClick(searchButton);

		return new SearchResultsPage(driver);

	}

// Clicking on Address Book Link that will return reference of Address Book Page

	public AddressBookEntriesPage navigateToAddressBookPage() {

		ut.doClickWithWait(addressBookLink, AppConstants.SHORT_DEFAULT_WAIT);

		return new AddressBookEntriesPage(driver);

	}

	// Clicking on Order History Link and returning the reference of that Page

	public OrderHistoryPage checkOrderHistory() {
		ut.waitVisibilityofElement(orderHistoryLink, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doClickWithWait(orderHistoryLink, AppConstants.SHORT_DEFAULT_WAIT);

		return new OrderHistoryPage(driver);

	}

}
