package com.testautomation.testcases;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testautomation.pageobject.HomePage;
import com.testautomation.pageobject.SignUp;
import com.testautomation.pageobject.accountCreationDetails;
import com.testautomation.pageobject.myAccountPage;
import com.testautomation.pageobject.registeredUserAccount;
import com.testautomation.utilities.ReadExcelFile;

public class LoginAndRegistration extends BaseClass{
	
	
	accountCreationDetails objAccountCreation = new accountCreationDetails(driver);
	registeredUserAccount ObjRegUser = new registeredUserAccount(driver);
	myAccountPage objMypg = new myAccountPage(driver);

	SignUp myAcpg = new SignUp(driver);
	HomePage objHp = new HomePage(driver);
	
	/*
	 * Email Gender Firstname LastName Password AddressFirstName AddressLastName
	 * Company address1 city state postcode country mobile no expected username
	 */
	
	
	@Test (dataProvider = "UserRegistrationDataProvider", enabled = true) 
	public void verifyRegistration(String Email,String Firstname,String data[])
	{

		logger.info("***************TestCase Verify Registration and Login starts*****************"); 

		objHp.clickOnSignIn();
	
		logger.info("Clicked on sign in link");
		
		myAcpg.signUpDetails(data[12],data[0]);
		
		logger.info("Email address & username entered in create account section.");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		myAcpg.clickSignup();

		logger.info("SignUp Successfully");
	
		//csv file needs to be imported
		
		objAccountCreation.UserDetailsRegistration(data);
		
		String frontEndUserName = ObjRegUser.getUserName();

		System.out.println("getuser name :-" +frontEndUserName);
		
		Assert.assertEquals(Firstname, frontEndUserName);

		logger.info("***************TestCase Verify Registration and Login ends*****************"); 

		ObjRegUser.clickOnSignOut();
	}
	

	
	@Test(dataProvider = "LoginDataProvider" , enabled = false)
	public void VerifyLogin(String email, String password, String expectedUsername) throws IOException 
	{

		logger.info("***************TestCase Verify Login starts*****************"); 

		objHp.clickOnSignIn();
		logger.info("Clicked on sign in link");
		objMypg.enterEmailAddress(email);
		logger.info("Entered email address");
		objMypg.enterPassword(password);
		logger.info("Entered password");
		objMypg.clickSignIn();
		logger.info("Clicked on sign in link..");

		
		String webUserName = ObjRegUser.getUserName();
		if(webUserName.equals(expectedUsername))
		{
			logger.info("VerifyLogin - Passed");
			ObjRegUser.clickOnSignOut();
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);

		}

		logger.info("***************TestCase Verify Login ends*****************"); 
	}

	@Test(dataProvider = "SignOutDataProvider" , enabled = false)
	public void VerifySignOut(String email, String password, String PageName)throws IOException 
	{

		logger.info("***************TestCase Verify Sign out starts*****************"); 
		
		
		objHp.clickOnSignIn();
		logger.info("Clicked on sign in link");
		objMypg.enterEmailAddress(email);
		logger.info("Entered email address");
		objMypg.enterPassword(password);
		logger.info("Entered password");
		objMypg.clickSignIn();
		logger.info("Clicked on sign in link..");
		ObjRegUser.clickOnSignOut();
		System.out.println("page title is :-" +objHp.getPageTitle());
		
		if(objHp.getPageTitle().equals(PageName))
		{
			logger.info("VerifySignOut - Passed");
			Assert.assertTrue(true);
		}

		else
		{
			logger.info("VerifySignOut - Failed");
			captureScreenShot(driver,"VerifySignOut");
			Assert.assertTrue(false);
		}

		logger.info("***************TestCase Verify Sign out ends*****************"); 

	}
	
	@DataProvider(name = "UserRegistrationDataProvider")
	public String[][] userRegistrationData()
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "UserRegistrationData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "UserRegistrationData");
	
		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"UserRegistrationData", i,j);
			}

		}
		return data;
	}

	
	
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");
	

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
			}

		}
		return data;
	}

	@DataProvider(name = "SignOutDataProvider")
	public String[][] signoutProvider()
	{
		
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "SignOutData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "SignOutData");
	

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"SignOutData", i,j);
			}

		}
		return data;
	}

}
