package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ForgetPwdPage {

	private WebDriver driver;

	private ElementUtil ut;

	private By emailAdd=By.id("input-email");
	
	private By continueBtn =By.xpath("//input[@value='Continue']");
	
	private By backBtn=By.linkText("Back"); 
	
	private By forgetPwdHeader=By.tagName("h1");	
	
	private By successMsg=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public ForgetPwdPage(WebDriver driver) {

		this.driver = driver;
		ut = new ElementUtil(this.driver);
	}
	
	public String forgetPwdHeaderAvailable()
	{
		
		
		String headerText=ut.doElementGetText(forgetPwdHeader);
		System.out.println("Header of Forget Pwd Page is: "+headerText);
		
		return headerText;
	}
	
	public boolean backBtnAvailable()
	{
		return ut.waitPresenceofElement(backBtn, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}

	public String enterEmailAddClickContinue(String emailAddre)
	{
		ut.waitPresenceofElement(emailAdd, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(emailAddre);
		ut.doClick(continueBtn);
		String successMessage=ut.doElementGetText(successMsg);
		System.out.println("Success Message on Forget Pwd Page is: "+successMessage);
		
		return successMessage;
	}
	

}
