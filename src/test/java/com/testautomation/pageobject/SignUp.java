package com.testautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.testcases.BaseClass;

public class SignUp extends BaseClass{
	
	
	WebDriver idriver;
	BaseClass objBC = new BaseClass();
	
	public SignUp(WebDriver rDriver)
	{
		idriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	//By signIn = By.xpath("//a[text()=' Signup / Login']");

	By userName = By.name("name");
	By signupEmail = By.xpath("//input[@data-qa='signup-email']");
	By btnSignUp = By.xpath("//button[text()='Signup']");
	
	public void signUpDetails(String username, String email)
	{
		objBC.enterText(userName, username);
		objBC.enterText(signupEmail, email);
	
	}

	public void clickSignup() {
		objBC.clickWebElement(btnSignUp);
	}
}
