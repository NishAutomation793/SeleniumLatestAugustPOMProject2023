package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;

	private ElementUtil ut;

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	private By searchResults = By.xpath("//div[@class='product-thumb']//h4/a");

	public ProductInfoPage clickSelectProduct(String product) {
		List<WebElement> allResults = ut.waitVisibilityofElements(searchResults, AppConstants.SHORT_DEFAULT_WAIT);

		for (WebElement el : allResults) {
			String text = el.getText();

			System.out.println("Products are: "+text);
			if (text.equalsIgnoreCase(product)) {

				el.click();
				break;

			}

		}
		return new ProductInfoPage(driver);

	}

}
