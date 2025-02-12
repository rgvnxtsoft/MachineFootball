package com.testautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.testcases.BaseClass;

public class registeredUserAccount {

	WebDriver ldriver;
	BaseClass objBC = new BaseClass();

	public registeredUserAccount(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	By userName = By.xpath("//a//b");

	By signOut = By.xpath("//a[text()=' Logout']");

	By searchBox = By.name("search_query");

	By submit_search = By.name("submit_search");

	By WomenMenu = By.linkText("Women");

	By TShirtMenu = By.linkText("T-shirts");
	
	
	public void clickOnSignOut() {

		objBC.clickWebElement(signOut);

	}

	public String getUserName() {
		return objBC.getText(userName);
		
	}

	public void EnterDataInSearchBox(String searchKey) {
		objBC.enterText(TShirtMenu, searchKey);
	}

	public void ClickOnSearchButton() {
		objBC.clickWebElement(submit_search);

	}

	public void MouseOverTShirtMenu() {

		objBC.hoverAndClick(searchBox, WomenMenu);

	}

}
