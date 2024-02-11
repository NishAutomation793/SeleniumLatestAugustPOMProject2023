package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {

		this.driver = driver;
		js = (JavascriptExecutor) this.driver;

	}

	public String getPageTitleByJS() {
		// Here document.title will return the Javascript string hence need to convert
		// it to Java string
		// return document.title --> in this return is the javascript return

		return js.executeScript("return document.title").toString();

	}

	public String getURLByJS() {
		// Here document.title will return the Javascript string hence need to convert
		// it to Java string

		return js.executeScript("return document.URL").toString();

	}

	public void generateJSALert(String mess) {

		js.executeScript("alert ('" + mess + "')");

		driver.switchTo().alert().accept();

	}

	public void generateJSPrompt(String mess, String promptMess) {

		js.executeScript("prompt ('" + mess + "')");

		Alert al = driver.switchTo().alert();

		al.sendKeys(promptMess);
		al.accept();
	}

	public void generateJSConfirm(String mess) {

		js.executeScript("confirm ('" + mess + "')");

		driver.switchTo().alert().accept();

	}

	/**
	 * This method is used to get the entire text of the Page via JS Executor which
	 * is not possible in case of Selenium
	 * 
	 * @return
	 */
	public String getEntirePageText() {
		return js.executeScript("return document.documentElement.innerText").toString();

	}

	public void scrollTillLastOfPage() {

		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	public void scrollTillStartOfPage() {

		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");

	}

	public void scrollSomePageUp(int height) {

		js.executeScript("window.scrollTo('" + height + "',0)");

	}

	public void scrollSomeDown(int height) {

		js.executeScript("window.scrollTo(0,'" + height + "')");

	}

	public void scrollTillMiddleOfPage() {

		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");

	}

	public void horizontalScroll() {

		js.executeScript("window.scrollBy(-100,-200)");
	}

	/**
	 * This method will scroll until that element is found in the page
	 * 
	 * @param ele
	 */
	public void scrollTillElementVisible(WebElement ele) {

		js.executeScript("arguments[0].scrollIntoView(true)", ele);

	}

	/**
	 * This method will help in zooming in and zooming out the page This script will
	 * only work in Edge,Safari,Chrome Browser
	 * 
	 * @param percentage
	 */
	public void zoomInzoomOut(int percentage) {

		String zoom = ("document.body.style.zoom='" + percentage + "%'");

		js.executeScript(zoom);
	}

	/**
	 * This method is used to draw a border around element
	 * 
	 * @param ele
	 */
	public void drawBorderAroundElement(WebElement ele) {

		js.executeScript("arguments[0].style.border= '3px solid red'", ele);

	}

	/**
	 * This method is used for highlighting the element or flashing the element or
	 * blinking
	 * 
	 * @param ele
	 */
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// Green
			changeColor(bgcolor, element);// Purple
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		// G->P->G->P

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public void clickUsingJs(WebElement ele) {

		js.executeScript("arguments[0].click()", ele);

	}

	public String pseudoElementHandle(String cssLocator) {

		String script = "return window.getComputedStyle(document.querySelector(" + cssLocator
				+ "),'::before').getPropertyValue('content');";

		return js.executeScript(script).toString();

	}
}
