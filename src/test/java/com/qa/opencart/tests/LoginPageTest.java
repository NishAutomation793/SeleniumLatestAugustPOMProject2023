package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

/**
 * In the Test Classes we should only focus on writing the Tests and Assertions
 * and validations of tests, we should never write the driver methods in the
 * Test Class
 */
public class LoginPageTest extends BaseTest {

	@Test
	public void LoginPageTitleTest() {

		String actualTitle = lp.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);

	}

	@Test
	public void LoginPageURLTest() {
		String url = lp.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));

	}

	@Test
	public void ifLogoExist() {

		Assert.assertTrue(lp.isLogoVisible());

	}

	@Test
	public void ifForgetPwdLinkExist() {

		Assert.assertTrue(lp.isForgetPwdLinkAvailale());

	}

	@Test(priority = 5)
	public void loginTest() {

		ap=lp.doLogin(pr.getProperty("userName"),pr.getProperty("password"));
		
		Assert.assertEquals(ap.getAccountPageTitle(),AppConstants.ACC_PAGE_TITLE);
	}

	@Test
	public void isSearchBoxAvailableTest() {

		String searchPlaceHolder = lp.searchBoxAvailable();
		Assert.assertEquals(searchPlaceHolder, "Search");

	}

	@Test
	public void footerLinksAvailableTest() {

		List<String> footerLinksAvailability = lp.footerLinksAvailability();

		Assert.assertTrue(footerLinksAvailability.contains("About Us"));

	}
}
