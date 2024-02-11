package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object [][] wrongTestData()
{

		return new Object[][]
				{
			{"", "Mah1"},
			{"Tish", ""},
			{"Fish", "Mah1"},
			{"", ""},
			{"*76789HJKl%^7","^%^&*^788HJK"}
			
				};
}
	
	@Test(dataProvider = "wrongTestData")
	public void negativeTest(String wrongUName, String wrongPass)
	{
		String warningMessaage=lp.doLoginWrongCredentials(wrongUName,wrongPass);
		
		Assert.assertEquals(warningMessaage,"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	
	
}
