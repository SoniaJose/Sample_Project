package com.snapEngage.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Class to check negative flow
 * 
 * @author Sonia
 *
 */
public class LoginTestcase2 {

	private static WebDriver driver;
	private static Properties property;
	private static FileInputStream inputStream;

	/**
	 * Method: To launch Chrome browser before executing the doLoginFail() method
	 * 
	 * @throws IOException
	 */
	@BeforeTest
	public void launchBrowser() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		property = new Properties();
		inputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		property.load(inputStream);
		driver.get(property.getProperty("url"));
	}

	/**
	 * Testcase1: To carryout login to portal using incorrect credentials
	 * 
	 * @throws IOException
	 */

	@Test
	public void doLoginFail() throws IOException {
		SoftAssert softAssert = new SoftAssert();
		driver.manage().window().maximize();
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys(property.getProperty("username"));
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(property.getProperty("password2"));
		WebElement signin = driver.findElement(By.name("Submit"));
		signin.submit();
		Boolean isDisplayed = driver.findElement(By.xpath("//form[@name='signin']/div[@class='msg']")).isDisplayed();
		if (isDisplayed.booleanValue()) {
			softAssert.fail("The username or password mentioned are incorrect");
		}
		driver.quit();
		softAssert.assertAll();
	}

}
