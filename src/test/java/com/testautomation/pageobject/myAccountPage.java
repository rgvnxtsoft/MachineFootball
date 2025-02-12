package com.testautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.testcases.BaseClass;

public class myAccountPage {

	//1. create object of webdriver
	WebDriver ldriver;
	BaseClass objBC = new BaseClass();

	//constructor
	public myAccountPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}


	By createEmailId = By.id("email_create");

	By SubmitCreate = By.id("SubmitCreate");

	By registeredUsersEmail = By.xpath("//input[@data-qa='login-email']");

	By registeredUsersPwd = By.xpath("//input[@data-qa='login-password']");

	By submitLogin = By.xpath("//button[@data-qa='login-button']");


	//identify action on webelement
	
	public void enterCreateEmailAddress(String emailAdd) 
	{
		objBC.enterText(createEmailId,emailAdd);
		
	}

	
	public void clickSubmitCreate()
	{
		objBC.clickWebElement(SubmitCreate);
	}
	
	//ACTIONS METHODS FOR ALREADY REGISTERED USERS
	
	public void enterEmailAddress(String emailAdd) 
	{
		objBC.enterText(registeredUsersEmail, emailAdd);

	}

	public void enterPassword(String pwd) 
	{
		objBC.enterText(registeredUsersPwd, pwd);
	
	}

	
	public void clickSignIn()
	{
		objBC.clickWebElement(submitLogin);

	}

}
