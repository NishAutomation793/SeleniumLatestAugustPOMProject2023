package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AddressBookEntriesPage {

	private WebDriver driver;

	private ElementUtil ut;

	private By newAddressBtn = By.linkText("New Address");

	private By firstName = By.id("input-firstname");

	private By lastName = By.id("input-lastname");
	private By company = By.id("input-company");

	private By address1 = By.id("input-address-1");

	private By address2 = By.id("input-address-2");
	private By city = By.id("input-city");

	private By postalCode = By.id("input-postcode");

	private By country = By.id("input-country");

	private By state = By.id("input-zone");

	private By continueBtn = By.xpath("//input[@value='Continue']");

	private By backBtn = By.linkText("Back");

	private By successmessage = By.xpath("//div[contains(@class,'alert')]");

	private By deleteAddress = By.linkText("Delete");

	private By NewAddressText = By.xpath("//div[@class='table-responsive']//tr/td[@class='text-left']");

	public AddressBookEntriesPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	public void addAddressBtnClick() {
		ut.doClickWithWait(newAddressBtn, AppConstants.SHORT_DEFAULT_WAIT);

	}

	public String addNewAddress(String fName, String lName, String comp, String add1, String add2, String cty,
			String posCode, String contry, String states) throws InterruptedException {

		addAddressBtnClick();

		ut.waitPresenceofElement(firstName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(fName);
		ut.doSendKeysWithWait(lastName, 10, lName);
		ut.doSendKeysWithWait(company, 10, comp);

		ut.doSendKeysWithWait(address1, 10, add1);
		ut.doSendKeysWithWait(address2, 10, add2);
		ut.doSendKeysWithWait(city, 10, cty);
		ut.doSendKeysWithWait(postalCode, 10, posCode);

		ut.doClickWithWait(country, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doSelectDropdownByText(country, contry);

		ut.doClickWithWait(state, AppConstants.SHORT_DEFAULT_WAIT);
		ut.waitVisibilityofElements(state, AppConstants.MEDIUM_DEFAULT_WAIT);
		Thread.sleep(3000);

		ut.doSelectDropdownByText(state, states);

		ut.doClickWithWait(continueBtn, AppConstants.SHORT_DEFAULT_WAIT);

		ut.waitVisibilityofElement(successmessage, AppConstants.MEDIUM_DEFAULT_WAIT);

		String message = ut.doElementGetText(successmessage);
		System.out.println("Success Message is: " + message);

		ut.doClickWithWait(backBtn, AppConstants.SHORT_DEFAULT_WAIT);

		return message;
	}

	public String deleteAllAddedAddresses() {
		List<WebElement> totalAddressSize = ut.getListWebElements(deleteAddress);
		System.out.println("Total count of address to be deleted is: " + totalAddressSize.size());
		String message = null;

		for (int i = 0; i < totalAddressSize.size(); i++) {

			WebElement delete = ut.getElement(deleteAddress);

			ut.waitVisibilityofElement(delete, AppConstants.MEDIUM_DEFAULT_WAIT);

			delete.click();

			ut.acceptJsAlertafterWait(AppConstants.SHORT_DEFAULT_WAIT);
			message = ut.doElementGetText(successmessage);

			if (!message.equalsIgnoreCase("Your address has been successfully deleted")) {
				break;
			} else {
				System.out.println("The Address has been deleted successfully");
			}

			driver.navigate().refresh();
		}
		return message;

	}

	public boolean checkAllNewAddedAddreses() {
		List<WebElement> allAddressText = ut.getListWebElements(NewAddressText);
		System.out.println("Total size is: " + allAddressText.size());

		for (WebElement el : allAddressText) {
			String text = el.getText();
			if (!text.isEmpty()) {
				System.out.println("Complete Address is: " + text);

			}

			else {
				System.out.println("No Address is Present...");
				return false;
			}
		}
		return true;
	}
}
