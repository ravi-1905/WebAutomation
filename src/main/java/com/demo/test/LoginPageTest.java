/**
Ravi Kumar
WebAutomation
*/
package com.demo.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.pages.LoginPage;

public class LoginPageTest extends BaseTest{
	
	public WebDriver driver;
	LoginPage loginPage;
	private Logger log = LogManager.getLogger(LoginPageTest.class.getName());

	@BeforeMethod
	public void classLevelSetup() throws IOException {
		driver = getDriver();
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		log.info("Browser closed");
	}
	
	@Test
	public void test_youtubeSearch() {
		loginPage.login("Admin","admin123");
	}
}
