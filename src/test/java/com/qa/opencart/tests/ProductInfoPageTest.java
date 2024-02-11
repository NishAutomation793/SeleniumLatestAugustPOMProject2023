package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

/**
 * This test class is still not running completely giving as productInfo as null
 * reference ref variables sp, productInfo are not assigned at global Before
 * Class level these are having references at test level and hence for next test
 * productInfo is coming as Null reference
 */
public class ProductInfoPageTest extends BaseTest {

	/**
	 * The below setup method is created as a prerequisite to login into app
	 * otherwise all tests will fail
	 */
	
	@BeforeClass
	public void productInfoSetUp() {

		ap = lp.doLogin(pr.getProperty("userName"), pr.getProperty("password"));

	}

//	@DataProvider
//	public Object[][] searchProductData() {
//
//		return new Object[][] { 
//			
//			    { "MacBook", "MacBook Air", "4" }, 
//			    { "Samsung", "Samsung Galaxy Tab 10.1", "7" },
//				{ "iMac", "iMac", "3" },
//				{ "iPod", "iPod Nano", "5" }
//
//		};
//
//	}

	@DataProvider
	public Object[][] searchExcelProductData()
	{
		Object obj[][]=ExcelUtil.getDatafromExcel(AppConstants.PRODUCT_DATA_SHEET_NAME);
		
		return obj;
	}
	
	@Test(dataProvider = "searchExcelProductData")
	
	public void checkProductImageCountTest(String searchKey, String productName, String imageCount) {

		sp = ap.searchProduct(searchKey);

		productInfo = sp.clickSelectProduct(productName);

		int actualCountproductInfo = productInfo.getaProductImageCount();

		Assert.assertEquals(String.valueOf(actualCountproductInfo), imageCount);
	}

	@Test
	public void checkProductDetailsTest() {
		sp = ap.searchProduct("MacBook");

		productInfo = sp.clickSelectProduct("MacBook Air");

		Map<String, String> productDetails = productInfo.getProductDetails();

		softAssert.assertEquals(productDetails.get("Product Name"), "MacBook Air");
		softAssert.assertEquals(productDetails.get("Product Price"), "$1,202.00");

		softAssert.assertEquals(productDetails.get("Brand"), "Apple");

		softAssert.assertEquals(productDetails.get("Product Code"), "Product 17");

		softAssert.assertEquals(productDetails.get("Reward Points"), "700");

		softAssert.assertEquals(productDetails.get("Availability"), "In Stock");

		softAssert.assertEquals(productDetails.get("Ex Tax"), "$1,000.00");

		softAssert.assertAll();

	}

}
