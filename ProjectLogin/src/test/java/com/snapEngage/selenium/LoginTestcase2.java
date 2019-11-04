package com.snapEngage.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginTestcase2 {
	
	public static WebDriver driver;
	public static Properties property;
	public static FileInputStream fis;
	public static WebDriverWait wait;
	
	/**
	 * Method: To launch chrome browser before executing the DoLoginFail()method
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
	 * Testcase1: To carryout login to portal using incorrect credentials 
	 * @throws IOException
	 */
	
	@Test
	public void doLoginFail() throws IOException 
	
	{
		driver.manage().window().maximize();   
		WebElement username =driver.findElement(By.id("email"));
		username.sendKeys(property.getProperty("username"));
		WebElement password =driver.findElement(By.id("password"));
		password.sendKeys(property.getProperty("password2"));
		WebElement signin =	driver.findElement(By.name("Submit"));
		signin.submit();
		Boolean s= driver.findElement(By.xpath("//form[@name='signin']/div[@class='msg']")).isDisplayed();
		if (s==true)
		Assert.fail("The username or password mentioned are incorrect");
	}





}
