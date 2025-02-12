package com.testautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.testautomation.testcases.BaseClass;

public class accountCreationDetails {


	WebDriver ldriver;
	BaseClass objBC = new BaseClass();


	public accountCreationDetails(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	By titleMrs = By.id("id_gender2"); // Title-Mrs
	By titleMr = By.id("id_gender1"); // Title-Mrs
	By custfirstName = By.id("name");
	By custlastName = By.id("email");
	By password = By.id("password");
	By addFirstname = By.id("first_name");
	By addLastname = By.id("last_name");
	By address1 = By.id("address1");
	By city = By.id("city");
	By state = By.id("state");
	By postcode = By.id("zipcode");
	By country = By.id("country");
	By phone_mobile = By.id("mobile_number");
	By register = By.xpath("//button[text()='Create Account']");
	By btnContinue = By.xpath("//a[text()='Continue']");

	//identify actions to be performed on web elements

	public void UserDetailsRegistration (String [] details){

		objBC.clickWebElement(titleMr);
		
		for (String data : details) {
	        String[] values = data.split(" ");
	        System.out.println("row details are: " + values);
	        objBC.enterText(custfirstName, values[0]);
			objBC.enterText(custlastName, values[1]);
			objBC.enterText(password, values[2]);
			objBC.clearTextField(addFirstname);
			objBC.enterText(addFirstname,values[3]);
			objBC.clearTextField(addLastname);
			objBC.enterText(addLastname,values[4]);
			objBC.enterText(address1, values[5]);
			objBC.enterText(city, values[6]);
			objBC.enterText(state, values[7]);
			objBC.enterText(postcode, values[8]);
			objBC.selectDropdownByVisibleText(country, values[9]);
			objBC.enterText(phone_mobile, values[10]);
		
		}
		objBC.clickWebElement(register);
		objBC.clickWebElement(btnContinue);
}
	
}

