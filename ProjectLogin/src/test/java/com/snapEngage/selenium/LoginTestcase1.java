package com.snapEngage.selenium;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestcase1 {
	

	public static WebDriver driver;
	public static Properties property;
	public static FileInputStream fis;
	public static WebDriverWait wait;
	
	/**
	 * Method: To idenitfy a web element using the locators and return a boolean value.
	 */
	public static boolean isElementPresent(By locator)
	{
		try {
		driver.findElement(locator);
		return true;
		}
		catch(Throwable t)
		{
			return false;
		}
	}
	
	/**
	 * Method: To launch chrome browser before executing the DoLoginPass()method
	 * @throws IOException
	 */
	
	@BeforeTest
	public void launchBrowser() throws IOException 
	{
		
	   WebDriverManager.chromedriver().setup();
	   driver = new ChromeDriver();
	   property = new Properties();
	   fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
	   property.load(fis);
	   driver.get(property.getProperty("url")); 
	   }
	
	/**
	 * Testcase1: To carry out successful login and later capture a screenshot. 
	 * @throws IOException
	 */

	@Test
	public void doLoginPass() throws IOException 
	
	{
		
	        SoftAssert softassert= new SoftAssert(); 
		
			driver.manage().window().maximize();   
			WebElement username =driver.findElement(By.id("email"));
			username.sendKeys(property.getProperty("username"));
			WebElement password =driver.findElement(By.id("password"));
			password.sendKeys(property.getProperty("password1"));
			WebElement signin =	driver.findElement(By.name("Submit"));
			signin.submit();
			wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='AccordionItemcss__StyledAccordionTitle-sc-1cl7j98-1 kJprYL']")));
			softassert.assertTrue(isElementPresent(By.xpath("//div[@class='AccordionItemcss__StyledAccordionTitle-sc-1cl7j98-1 kJprYL']")),"page not loaded completely");
			File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
			FileUtils.copyFile(screenshot, new File(property.getProperty("screenshot.basedir")));
			softassert.assertAll();
			}
	/**
	 * Testcase2: To carry out successful logout when the logout button is found and close the browser once completed.
	 */
	
	@Test
	public void logOutUser()
	
	{
			
			Assert.assertTrue(isElementPresent(By.className("avatar")),"Logout button not present");
			driver.findElement(By.className("avatar")).click();
			driver.findElement(By.xpath("//li[@data-testid='Logout']")).click();
			driver.findElement(By.xpath("//button[@name='ok']")).click();
			driver.quit();
	}

	

	
}

