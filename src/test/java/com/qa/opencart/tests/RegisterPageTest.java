package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {
		registerPage = lp.naviagteToRegistrationPage();

	}
	
	public String getRandomEmail()
	{
		return "testAutomation"+System.currentTimeMillis()+"@opencart.com";
		//return "testAutomation"+UUID.randomUUID()+"@opencart.com";

	}

//	@DataProvider
//	public Object[][] getUserRegData() {
//		
//		
//		return new Object[][]
//				{
//			{"Mish", "Mah1", "8765678777", "mish@123",
//			"mish@123", "yes"},
//			{"Tish", "Mah1", "8765238777", "tish@123",
//				"tish@123", "yes"},
//			{"Fish", "Mah1", "8765675577", "fish@123",
//					"fish@123", "no"},
//			
//				};
//	}
	
	@DataProvider
	public Object[][] getUserRegExcelData()
	{
		
		Object obj[][]=ExcelUtil.getDatafromExcel(AppConstants.USER_REG_SHEET_NAME);
		
		return obj;
		
	}
	
	
	@Test(dataProvider = "getUserRegExcelData")
	public void checkUserRegisterTest(String fName, String lName, String phone, String pass, String subs) {
		String userMessage = registerPage.userRegistration(fName, lName, getRandomEmail(), phone, pass,
				subs);

		Assert.assertEquals(userMessage, AppConstants.USER_REGISTER_SUCCESS_MESSAGE);

	}

}
