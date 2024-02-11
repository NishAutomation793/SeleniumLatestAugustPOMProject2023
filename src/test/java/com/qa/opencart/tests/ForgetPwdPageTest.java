package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ForgetPwdPageTest extends BaseTest{

	@BeforeClass
	public void productInfoSetUp() {

		forgetPwdPage=lp.navigateForgetPwdPage();

	}
	
	@Test
	public void backBtnAvailableTest()
	{
		Assert.assertTrue(forgetPwdPage.backBtnAvailable());
		
	}
	
	@Test
	public void forgetPwdHeaderTest()
	{
		String header=forgetPwdPage.forgetPwdHeaderAvailable();
		Assert.assertEquals(header,AppConstants.FORGET_PWD_HEADER_NAME);
		
	}
	
	@Test
	public void getNewPasswordviaEmail()
	{
		String successMessage=forgetPwdPage.enterEmailAddClickContinue(pr.getProperty("userName"));
		
		Assert.assertEquals(successMessage,AppConstants.FORGET_PWD_SUCCESS_MESSAGE);
		
	}
}
