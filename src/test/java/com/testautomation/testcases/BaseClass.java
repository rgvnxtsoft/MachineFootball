package com.testautomation.testcases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.testautomation.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig rc = new ReadConfig();
	
	String url = rc.getBaseURL();
	String browser = rc.getBrowser();

	public static WebDriver driver;
	public static Logger logger;

	
	@BeforeClass
	public void setup()
	{

		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		
		default:
			driver = null;
			break;

		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		logger = LogManager.getLogger("TestAutomationV1");
		driver.get(url);
		logger.info("url opened");
}
	
	//user method to capture screen shot
		public void captureScreenShot(WebDriver driver,String testName) throws IOException
		{
			TakesScreenshot screenshot = ((TakesScreenshot)driver);
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");
			FileUtils.copyFile(src, dest);
		}
		
		public void clickWebElement(By locator) {
		    WebElement element = driver.findElement(locator);
		    element.click();
		}
		
		public void enterText(By locator, String text) {
		    WebElement element = driver.findElement(locator);
		    element.sendKeys(text);
		}

		public void selectDropdownByVisibleText(By locator, String visibleText) {
		    WebElement dropdown = driver.findElement(locator);
		    Select select = new Select(dropdown);
		    select.selectByVisibleText(visibleText);
		}
		
		public void clearTextField(By locator) {
		    WebElement element = driver.findElement(locator);
		    element.clear();  // Clears the content of the input field
		}

		public void selectRadioButton(By locator) {
		    WebElement radioButton = driver.findElement(locator);
		    if (!radioButton.isSelected()) {
		        radioButton.click();  // Select the radio button if not already selected
		    }
		}

		public String getText(By locator) {
		    WebElement element = driver.findElement(locator);
		    return element.getText();
		}

		public void waitForElementToBeVisible(By locator, int timeout) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public void waitForElementToBeClickable(By locator, int timeout) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		    wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public void switchToWindowByTitle(String windowTitle) {
		    Set<String> windows = driver.getWindowHandles();
		    for (String window : windows) {
		        driver.switchTo().window(window);
		        if (driver.getTitle().equals(windowTitle)) {
		            break;
		        }
		    }
		}
		
		public void scrollToElement(By locator) {
		    WebElement element = driver.findElement(locator);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", element);
		}


		public void acceptAlert() {
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		}

		public void dismissAlert() {
		    Alert alert = driver.switchTo().alert();
		    alert.dismiss();
		}

		public String getAlertText() {
		    Alert alert = driver.switchTo().alert();
		    return alert.getText();
		}

		public void hoverAndClick(By hoverLocator, By clickLocator) {
			Actions actions = new Actions(driver);  
			WebElement hoverElement = driver.findElement(hoverLocator);  
		    WebElement clickElement = driver.findElement(clickLocator);  
		    actions.moveToElement(hoverElement).moveToElement(clickElement).click().build().perform();                    
		}

	
	@AfterClass
	public void tearDownDriver()
	{
		driver.close();
		driver.quit();
	}

}
