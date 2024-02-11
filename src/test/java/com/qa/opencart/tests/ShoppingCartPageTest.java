package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ShoppingCartPageTest extends BaseTest {

	@BeforeClass
	public void SetUp() {

		ap = lp.doLogin(pr.getProperty("userName"), pr.getProperty("password"));

	}

	@Test
	public void productOrderPlacedTest() {
		sp = ap.searchProduct("iPod");
		productInfo = sp.clickSelectProduct("iPod Touch");

		String cartAddSuccessMessage = productInfo.addToCart();

		softAssert.assertTrue(cartAddSuccessMessage.contains("Success"));

		shopCartPage = productInfo.navigateToshoppingCartPage();

		String checkOutHeader = shopCartPage.clickCheckout();

		softAssert.assertEquals(checkOutHeader, "Checkout");

		String orderPlacedMess = shopCartPage.finalCheckOutProduct();

		softAssert.assertEquals(orderPlacedMess, AppConstants.SUCCESS_ORDER_PLACED_MESSAGE);

		softAssert.assertAll();

	}
}
