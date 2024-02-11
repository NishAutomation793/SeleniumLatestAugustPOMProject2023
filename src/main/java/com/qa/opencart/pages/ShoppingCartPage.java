package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;

	private ElementUtil ut;

	public ShoppingCartPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	private By headerText = By.tagName("h1");

	private By coupanCode = By.linkText("Use Coupon Code");
	private By shippingTaxes = By.linkText("Estimate Shipping & Taxes");
	private By giftCertificate = By.linkText("Use Gift Certificate");

	private By checkOutBtn = By.linkText("Checkout");

	private By checkOutHeader = By.tagName("h1");

	private By billingDetails = By.linkText("Step 2: Billing Details");

	private By continueBtnPayment = By.cssSelector("input#button-payment-address");

	private By continueBtnShipping = By.cssSelector("input#button-shipping-address");

	private By continueBtnShippingMethod = By.cssSelector("input#button-shipping-method");

	private By termsCondition = By.xpath("//input[@name='agree']");

	private By continueBtnPaymentMethod = By.cssSelector("input#button-payment-method");

	private By confrmOrdrBtn = By.cssSelector("input#button-confirm");

	private By successOrderPlaced = By.xpath("//div[@id='content']/h1[text()='Your order has been placed!']");

	public String shopCartHeaderName() {
		return ut.doElementGetText(headerText);

	}

	public String clickCheckout() {
		
		System.out.println(ut.doElementGetText(headerText));
		ut.waitPresenceofElement(coupanCode, AppConstants.SHORT_DEFAULT_WAIT).click();
		ut.doClickWithWait(shippingTaxes, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doClickWithWait(giftCertificate, AppConstants.SHORT_DEFAULT_WAIT);
		ut.doClickWithWait(checkOutBtn, AppConstants.SHORT_DEFAULT_WAIT);
		
		System.out.println("Checkout Header is: "+ut.doElementGetText(checkOutHeader));
		
		return ut.waitPresenceofElement(checkOutHeader, AppConstants.SHORT_DEFAULT_WAIT).getText();

	}

	public String finalCheckOutProduct(){
		ut.waitVisibilityofElement(billingDetails, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(continueBtnPayment, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(continueBtnShipping, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(continueBtnShippingMethod, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(termsCondition, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(continueBtnPaymentMethod, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitVisibilityofElement(confrmOrdrBtn, AppConstants.SHORT_DEFAULT_WAIT).click();

		ut.waitPresenceofElement(successOrderPlaced, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String successMessage=ut.doElementGetText(successOrderPlaced);
		
		System.out.println("Successful Order Placement Message is: "+successMessage);
		return successMessage;
	}
}
