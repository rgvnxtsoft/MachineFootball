package com.testautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.testcases.BaseClass;

public class HomePage extends BaseClass {

	// Identify WebElements across page
	
	By signIn = By.xpath("//a[text()=' Signup / Login']");
	By tshirtMenu =  By.xpath("(//a[text()='T-shirts'])[2]");

	
	WebDriver idriver;
	BaseClass objBC = new BaseClass();
	
	public HomePage(WebDriver rDriver)
	{
		idriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	
	//click element
	public void clickOnSignIn()
	{
		objBC.clickWebElement(signIn);
	}
	
	
	public void clickOnTShirtMenu()
	{
		objBC.clickWebElement(tshirtMenu);
	}
	
	
	public String getPageTitle()
	{
		return(idriver.getTitle());
	}
	
	

}
