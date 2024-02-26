package com.qa.opencart.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ForgetPwdPageTest extends BaseTest{
	
	private static final Logger log=LogManager.getLogger(ForgetPwdPageTest.class);


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
		
		log.info("Sucess Message is: "+successMessage);
		
		Assert.assertEquals(successMessage,AppConstants.FORGET_PWD_SUCCESS_MESSAGE);
		
	}
}
