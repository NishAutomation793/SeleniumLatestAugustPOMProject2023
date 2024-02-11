package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;

	}

//	DOM:Document Object Model: HTML Code
	// 1st:
//	driver.findElement(By.id("input-email")).sendKeys("test@gmail.com");
//	driver.findElement(By.id("input-password")).sendKeys("test@123");

	// 2nd:
//	WebElement emailId = driver.findElement(By.id("input-email"));
//	WebElement password = driver.findElement(By.id("input-password"));
//	
//	emailId.sendKeys("test@gmail.com");
//	password.sendKeys("test@123");

	// 3rd: By locator
//	By eId = By.id("input-email");
//	By pwd = By.id("input-password");
//	
//	WebElement emailId = driver.findElement(eId);
//	WebElement password = driver.findElement(pwd);
//	
//	emailId.sendKeys("test@gmail.com");
//	password.sendKeys("test@123");

	// 4th: By locator + generic method for element
//	By eId = By.id("input-email");
//	By pwd = By.id("input-password");
//	
//	getElement(eId).sendKeys("test@gmail.com");
//	getElement(pwd).sendKeys("test@123");

	// 5th: By locator + generic method for element and action
//	By eId = By.id("input-email");
//	By pwd = By.id("input-password");
//	
//	doSendKeys(eId, "test@gmail.com");
//	doSendKeys(pwd, "test@123");

	// 6th: By locator + generic method for element and action in other element util
	// class
//	By eId = By.id("input-email");
//	By pwd = By.id("input-password");
//	
//	ElementUtil eleUtil = new ElementUtil(driver);
//	eleUtil.doSendKeys(eId, "test@gmail.com");
//	eleUtil.doSendKeys(pwd, "test@123");

	// 7th: BrowserUtil + ElementUtil

	// 8th: By locators --> String locators
//	By eId = By.id("input-email");
//	By pwd = By.id("input-password");
//  9th: POM + framework

	public WebElement getElement(By locator) {
		return driver.findElement(locator);

	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public void doSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value);

	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();

	}

	public String getElementAttribute(By locator, String attName) {
		return getElement(locator).getAttribute(attName);

	}

	public List<WebElement> getListWebElements(By locator) {
		return driver.findElements(locator);

	}

	public int getElementsCount(By locator) {
		return getListWebElements(locator).size();

	}

	/**
	 * 
	 * @param locator
	 * @return List of textValues after creating a blank Array List to store all the
	 *         text Values
	 */
	public List<String> getElementsTextList(By locator) {
		List<WebElement> listWebElements = getListWebElements(locator);

		List<String> elementText = new ArrayList<String>();
		for (int i = 0; i < listWebElements.size(); i++) {
			String textValue = listWebElements.get(i).getText();

			if (textValue.length() != 0) {

				elementText.add(textValue);
			}

		}
		return elementText;

	}

	/**
	 * 
	 * @param locator
	 * @param attrName
	 * @return List of String of corresponding Value to Attribute Name.
	 */
	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> listWebElements = getListWebElements(locator);

		List<String> elementAttribute = new ArrayList<String>();
		for (int i = 0; i < listWebElements.size(); i++) {
			String attrValue = listWebElements.get(i).getAttribute(attrName);

			elementAttribute.add(attrValue);

		}
		return elementAttribute;
	}

	public void googleSearchSuggestion(By searchBox, String searchName, By suggList, String suggName)
			throws InterruptedException {

		doSendKeys(searchBox, searchName);
		Thread.sleep(3000);

		List<WebElement> listElements = getListWebElements(suggList);

		for (WebElement e : listElements) {

			String text = e.getText();
			System.out.println("Entire List after Searching is :" + text);

			if (text.equalsIgnoreCase(suggName))

			{

				e.click();
				break;
			}

		}
	}

	public void clickElementText(By locator, String eleText) {

		List<WebElement> findElements = getListWebElements(locator);

		for (WebElement ele : findElements)

		{
			System.out.println(ele.getText());

			if (ele.getText().equals(eleText)) {
				ele.click();

				System.out.println("Title of the page with" + eleText + "language is " + driver.getTitle());
				break;
			}
		}
	}

	public boolean checkSingleElementonPage(By locator) {
		return getListWebElements(locator).size() == 1 ? true : false;

	}

	public boolean checkMultipleElementsonPage(By locator) {
		return getListWebElements(locator).size() >= 1 ? true : false;

	}

// ***************Select Class Dropdown Utilities*******************	

	public void doSelectDropdownByIndex(By locator, int index) {

		WebElement ele = getElement(locator);

		Select select = new Select(ele);
		select.selectByIndex(index);

	}

	public void doSelectDropdownByValue(By locator, String value) {

		WebElement ele = getElement(locator);

		Select select = new Select(ele);
		select.selectByValue(value);

	}

	public void doSelectDropdownByText(By locator, String text) {

		WebElement ele = getElement(locator);

		Select select = new Select(ele);
		select.selectByVisibleText(text);

	}

	public int getDorpdownOptionsCount(By locator) {

		Select select = new Select(getElement(locator));

		return select.getOptions().size();

	}

	public List<String> getAllDropDownOptions(By locator) {

		WebElement ele = getElement(locator);

		Select select = new Select(ele);

		List<WebElement> options = select.getOptions();
		List<String> list = new ArrayList<String>();

		for (WebElement e : options) {

			list.add(e.getText());

		}

		return list;

	}

	public void selectAllDropDownOptions(By Locator) {
		Select select = new Select(getElement(Locator));
		List<WebElement> options = select.getOptions();

		if (select.isMultiple()) {

			for (WebElement e : options) {
				String text = e.getText();

				select.selectByVisibleText(text);

			}
		} else {
			System.out.println("This dropdown is not of multiple type");

		}

	}

//***************************Actions Utils*****************************************	

	public void parentChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();

		Thread.sleep(2000);
		doClick(childMenuLocator);

	}

	/**
	 * 
	 * @param parentMenuLocator
	 * @param subMenuLocator
	 * @param subMenuChild1Locator
	 * @param subMenuChild2Locator
	 * @throws InterruptedException
	 * 
	 *                              This method is to handle multiple submenu after
	 *                              clicking on the main menu like bigbasket
	 *                              application
	 */
	public void multiMenuHandle(By parentMenuLocator, By subMenuLocator, By subMenuChild1Locator,
			By subMenuChild2Locator) throws InterruptedException {

		doClick(parentMenuLocator); // Clicking on the main menu tab
		Actions act = new Actions(driver);
		act.moveToElement(getElement(subMenuLocator)).build().perform();
		Thread.sleep(2000);

		act.moveToElement(getElement(subMenuChild1Locator)).build().perform();
		Thread.sleep(2000);
		doClick(subMenuChild2Locator);

	}

	public void doActionSendKeys(By Locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(Locator), value).build().perform();

	}

	public void doActionClick(By Locator) {
		Actions act = new Actions(driver);
		act.click(getElement(Locator)).build().perform();

	}

	public void doActionSendKeysWithPause(By Locator, String str) {
		Actions act = new Actions(driver);

		char ch[] = str.toCharArray();

		for (char c : ch) {

			act.sendKeys(getElement(Locator), String.valueOf(c)).pause(1000).build().perform();

		}
	}

//******************************WebDriver Waits Utility***********************************

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timOut
	 * @return
	 */
	public WebElement waitPresenceofElement(By locator, int timOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * This is the overloaded method
	 * 
	 * @param locator
	 * @param timOut
	 * @param pollTime
	 * @return
	 */
	public WebElement waitPresenceofElement(By locator, int timOut, int pollTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut), Duration.ofSeconds(pollTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timOut
	 * @return
	 */
	public WebElement waitVisibilityofElement(By locator, int timOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitVisibilityofElement(WebElement ele, int timOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut));
		return wait.until(ExpectedConditions.visibilityOf(ele));

	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * This is the overloaded method
	 * 
	 * @param locator
	 * @param timOut
	 * @param pollTime
	 * @return
	 */
	public WebElement waitVisibilityofElement(By locator, int timOut, int pollTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timOut), Duration.ofSeconds(pollTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timOut
	 * @return
	 */
	public List<WebElement> waitVisibilityofElements(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timOut
	 * @return
	 */
	public List<WebElement> waitPresenceofElements(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	
	

	public void doClickWithWait(By locator, int timeOut) {
		waitPresenceofElement(locator, timeOut).click();

	}

	public void doSendKeysWithWait(By locator, int timeOut, String value) {

		waitVisibilityofElement(locator, timeOut).sendKeys(value);

	}

	public String waitTillTitleVisible(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.titleContains(title))) {

			return driver.getTitle();

		}

		return null;
	}

	public String waitforURLContains(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.urlContains(url))) {

			return driver.getCurrentUrl();

		}

		return null;
	}

	public Alert waitTillAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void acceptJsAlertafterWait(int timeOut) {

		waitTillAlertPresent(timeOut).accept();

	}

	public void rejectJsAlertafterWait(int timeOut) {

		waitTillAlertPresent(timeOut).dismiss();

	}

	public void enterValueJsAlertafterWait(int timeOut, String value) {

		waitTillAlertPresent(timeOut).sendKeys(value);

	}

	public String getTextAlertafterWait(int timeOut) {

		return waitTillAlertPresent(timeOut).getText();
	}

//These 4 methods will wait for the frame to load and switches also internally.	
	public void frameAvailableAandSwitchwithWaitByLocator(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	public void frameAvailableAandSwitchwithWaitByIdorName(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));

	}

	public void frameAvailableAandSwitchwithWaitByWebElement(int timeOut, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));

	}

	public void frameAvailableAandSwitchwithWaitByIndex(int timeOut, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));

	}

//Here we have put try catch block because if the condition doesn't match then time out expection is thrown w/o going to catch block	
	public boolean checkTotalWindows(int expectedSize, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {

			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedSize))) {

				return true;
			}
		}

		catch (TimeoutException e) {
			System.out.println("Number of windows doesn't match");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void checklementClickablewithWait(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

//********************Fluent Wait Utility*************************
	public WebElement checkingElementwithFluentWait(By locator, int timeOut, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("Time out is over. No element")
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


	}
	
	public void checkingFramewithFluentWait(String idOrName, int timeOut, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).withMessage("Time out is over. No such Frame Exception")
				.ignoring(NoSuchFrameException.class);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));

	}

	public Alert checkingAlertwithFluentWait(int timeOut, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).withMessage("Time out is over. No such Alert Exception")
				.ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());

	}
	
//*******************For checking if Page loaded completely**************
	
/**
 * Three states of page loading are : loading, interactive, complete
 * @param timeOut
 * @return
 */
	public boolean isPageLoadedCompletely(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		String state=wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==='complete'")).toString();
	return Boolean.parseBoolean(state);
	
	}
}