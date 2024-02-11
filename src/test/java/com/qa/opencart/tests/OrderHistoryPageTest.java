package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;


public class OrderHistoryPageTest extends BaseTest {

	@BeforeClass
	public void SetUp() {

		ap = lp.doLogin(pr.getProperty("userName"), pr.getProperty("password"));

	}

	@Test
	public void checkOrderHistoryListTest() throws InterruptedException
	{
		orderHisPage=ap.checkOrderHistory();
		boolean falg=orderHisPage.getListOfOrders();
		Assert.assertTrue(falg);
		
	}
	
}
