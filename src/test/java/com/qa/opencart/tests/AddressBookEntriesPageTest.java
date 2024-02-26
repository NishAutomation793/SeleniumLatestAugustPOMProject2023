package com.qa.opencart.tests;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ShoppingCartPage;
import com.qa.opencart.utils.ExcelUtil;

public class AddressBookEntriesPageTest extends BaseTest {
	
	private static final Logger log=LogManager.getLogger(AddressBookEntriesPageTest.class);


	@BeforeClass
	public void accSetUp() {

		ap = lp.doLogin(pr.getProperty("userName"), pr.getProperty("password"));
	}

	public String getRandomAddress1() {

		return "Address1 " + UUID.randomUUID();

	}

	public String getRandomAddress2() {

		return "Address2 " + UUID.randomUUID();

	}

	@DataProvider
	public Object[][] getAddressFromExcel() {

		Object obj[][] = ExcelUtil.getDatafromExcel(AppConstants.ADDRESS_DATA_SHEET_NAME);

		return obj;
	}

	@Test(dataProvider = "getAddressFromExcel")
	public void createNewAddressEntriesTest(String fName, String lName, String comp, String cty, String posCode,
			String contry, String states ) throws InterruptedException{

		addBookEntriesPage = ap.navigateToAddressBookPage();

		String actualMessage=addBookEntriesPage.addNewAddress(fName, lName, comp, getRandomAddress1() , getRandomAddress2() , cty, posCode, contry, states);

		Assert.assertEquals(actualMessage,AppConstants.ADD_NEW_ADDRESS_SUCCESS_MESSAGE);
		
	}
	
	
	@Test(priority = 2)
	public void checkAllAddedAddressesEntriesTest()
	{
		addBookEntriesPage = ap.navigateToAddressBookPage();
		boolean flag=addBookEntriesPage.checkAllNewAddedAddreses();
		
		Assert.assertEquals(flag, true);
		
	}
	
	
	@Test(priority = 3)
	public void checkAllAddressesDeletedTest()
	{
		addBookEntriesPage = ap.navigateToAddressBookPage();
		String message=addBookEntriesPage.deleteAllAddedAddresses();
		//System.out.println("Message after deleting all entries is: "+message);
		
		log.info("Message after deleting all entries is: "+message);
		Assert.assertEquals(message,"Warning: You must have at least one address!");;
	}

}
