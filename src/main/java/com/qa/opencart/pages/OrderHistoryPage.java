package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class OrderHistoryPage {

	private WebDriver driver;

	private ElementUtil ut;

	private By orderHistoryHeader = By.tagName("h1");

	private By orderHisTableHeaders = By.xpath("//table[@class='table table-bordered table-hover']/thead/tr");

	private By orderHistoryTableList = By.xpath("//table[@class='table table-bordered table-hover']//tbody/tr");

	private By nextArrowKey = By.xpath("//ul[@class='pagination']/li/a");

	public OrderHistoryPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}

	public String checkOrderHistoryHeader() {
		return ut.doElementGetText(orderHistoryHeader);

	}

	public String getOrderHistoryCurrentUrl() {
		String orderHistoryUrl = ut.waitforURLContains(AppConstants.ORDER_HISTORY_URL_FRACTION,
				AppConstants.SHORT_DEFAULT_WAIT);

		System.out.println("Order History url is: " + orderHistoryUrl);

		return orderHistoryUrl;

	}

	public boolean getListOfOrders() {
		List<WebElement> orderTableHeaders = ut.getListWebElements(orderHisTableHeaders);

		for (WebElement el : orderTableHeaders) {
			System.out.println(el.getText());

		}

		List<WebElement> orderHistoryList = ut.getListWebElements(orderHistoryTableList);

		System.out.println("Order List is: " + "with total size is " + orderHistoryList.size());

		for (WebElement el : orderHistoryList) {
			String text = el.getText();
			System.out.println(text);
		}
		return true;
	}

}
