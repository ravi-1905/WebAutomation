/**
Ravi Kumar
WebAutomation
*/
package com.demo.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.pages.DashboardPage;
import com.demo.pages.LoginPage;

public class DashboardTest extends BaseTest{

	DashboardPage dashboardPage;
	LoginPage loginPage;
	private Logger log = LogManager.getLogger(DashboardTest.class.getName());
	
	@BeforeMethod
	public void classLevelSetup() throws IOException {
		driver = getDriver();
		dashboardPage = new DashboardPage(driver);
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		log.info("Browser closed");
	}
	
	@Test
	public void test_SearchForDirectory() {
		loginPage.login("Admin","admin123");
		dashboardPage.searchDashboard("Direc");
	}
}
