package com.qa.opencart.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {

	
/**
* The object of AccountsPage create in doLOgin method is 
* being caught by ap as reference variable declared at BaseTest Class level	
* The below setup method is created as a prerequisite to login into app otherwise all tests will fail
* 		
*/
	@BeforeClass
	public void accSetUp() {

		ap = lp.doLogin(pr.getProperty("userName"), pr.getProperty("password"));

	}

	@Test
	public void checkLogOutLinkTest() {

		Assert.assertTrue(ap.checkLogoutLink());
	}

	@Test
	public void checkAccountTitleTest() {

		String actTitle = ap.getAccountPageTitle();

		Assert.assertEquals(actTitle, AppConstants.ACC_PAGE_TITLE);

	}

	@Test
	public void checkAccountUrl() {

		String actualUrl = ap.getAccountPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACC_PAGE_URL_FRACTION));

	}

	@Test
	public void getAccountLinks() {

		List<String> headersLinks = ap.getHeadersLinks();
		System.out.println("Headers Links are: " + headersLinks);
	}

	@Test
	public void getAccountHeaderText() {

		List<String> headersText = ap.getHeadersText();
		System.out.println("Headers Text are: " + headersText);

		Assert.assertEquals(headersText, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void getAccountHeaderLinkList() {

		List<String> headersLinks = ap.getHeadersLinks();
		System.out.println("Headers Text are: " + headersLinks);

		Assert.assertTrue(headersLinks.contains("Tablets"));
	}

//Here we are searching product on Account Page and 
//then clicking on the search results and then asserting that we have clicked the right product for us	

	@Test
	public void searchProductTest() {
		sp = ap.searchProduct("MacBook");
		productInfo = sp.clickSelectProduct("MacBook Air");

		String actualProductName = productInfo.checkProductName();

		Assert.assertEquals(actualProductName, "MacBook Air");

	}

}
