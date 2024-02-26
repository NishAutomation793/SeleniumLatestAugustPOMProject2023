package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;

	private ElementUtil ut;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	private By productName = By.tagName("h1");

	private By productImages = By.cssSelector("ul.thumbnails a");

	private By productInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");

	private By productPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private By addToCartBtn =By.cssSelector("button#button-cart");
	
	private By shoppingCartLink=By.linkText("shopping cart");
	
	private By addingCartSuccessMessage=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	private Map<String, String> productInfoMap = new HashMap<String, String>();

	private static final Logger log=LogManager.getLogger(ProductInfoPage.class);

	
	public String checkProductName() {

		String productText = ut.doElementGetText(productName);
		//System.out.println("Product Name is: " + productText);

		log.info("Product Name is: " + productText);
		return productText;

	}

	public int getaProductImageCount() {
		int countProduct = ut.getElementsCount(productImages);

		return countProduct;

	}

	public void getProductMetaDataInfo() {
		List<WebElement> productExtraInfo = ut.waitVisibilityofElements(productInfo, AppConstants.SHORT_DEFAULT_WAIT);

		for (WebElement el : productExtraInfo) {

			String productText = el.getText();
			String metaKey = productText.split(":")[0].trim();
			String metaValue = productText.split(":")[1].trim();
			productInfoMap.put(metaKey, metaValue);

		}
	}

	public void getProductPricing() {
		List<WebElement> productPriceList = ut.waitVisibilityofElements(productPrice, AppConstants.SHORT_DEFAULT_WAIT);

		String productPrice = productPriceList.get(0).getText();
		String externalTaxKey = productPriceList.get(1).getText().split(":")[0].trim();
		String externalTaxValue = productPriceList.get(1).getText().split(":")[1].trim();

		productInfoMap.put(externalTaxKey, externalTaxValue);
		productInfoMap.put("Product Price", productPrice);
	}

	public Map<String, String> getProductDetails() {
		productInfoMap.put("Product Name", checkProductName());
		getProductMetaDataInfo();
		getProductPricing();
		//System.out.println("All the Product Details are: " + productInfoMap);

		log.info("All the Product Details are: " + productInfoMap);
		return productInfoMap;

	}

//Adding to cart the particular product and then moving to shopping cart page	
	public String addToCart()
	{
		ut.doClickWithWait(addToCartBtn, AppConstants.SHORT_DEFAULT_WAIT);
		
		ut.waitPresenceofElement(addingCartSuccessMessage, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String addCartSuccessMessage=ut.doElementGetText(addingCartSuccessMessage);
		
		//System.out.println("Adding Cart Message is: "+addCartSuccessMessage);
		
		log.info("Adding Cart Message is: "+addCartSuccessMessage);
		return addCartSuccessMessage ;
		
	}
	
	public ShoppingCartPage navigateToshoppingCartPage()
	{
		ut.waitPresenceofElement(shoppingCartLink, AppConstants.SHORT_DEFAULT_WAIT).click();
		return new ShoppingCartPage(driver);
		
	}

	

}
