package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.AddressBookEntriesPage;
import com.qa.opencart.pages.ForgetPwdPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.OrderHistoryPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

/**
 * All the Main Page references should be maintained in the Base Test Class only
 * Try to keep all driver factor, WebDriver references here itself.
 */
public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage lp;
	protected AccountsPage ap;
	
	protected Properties pr;
	
	protected ProductInfoPage productInfo;
	
	protected SearchResultsPage sp;
	
	protected SoftAssert softAssert;

	protected RegisterPage registerPage;
	protected ForgetPwdPage forgetPwdPage;
	
	protected AddressBookEntriesPage addBookEntriesPage;
	
	protected ShoppingCartPage shopCartPage;
	protected OrderHistoryPage orderHisPage;

	
	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional ("chrome") String browserNamefromXml) {
//Here above Optional annotation is used when we are 
//not having any parameters for test case from textNG.xml and this method is expecting some parameter
		df = new DriverFactory();
		pr=df.initProp();
	
		if(browserNamefromXml!=null)
		{
			pr.setProperty("browser", browserNamefromXml);
			
		}
//This above logic we have written if we get some browser parameter 
//from testNg.xml file then properties files will be udpated with the xml browser parameter value.
//Preference will be given to testNg.xml file parameters over prop files.				
		
		driver = df.intDriver(pr);
		lp = new LoginPage(driver);
		softAssert=new SoftAssert();
		
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
