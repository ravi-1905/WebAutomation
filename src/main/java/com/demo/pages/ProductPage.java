/**
Ravi Kumar
WebAutomation
*/
package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.utils.Verification;

public class DashboardPage extends BasePage{

	Verification verify;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		verify = new Verification(driver);
	}
	
	By search = By.xpath("//input[@placeholder='Search']");
	By searchResult = By.xpath("//a[@href='/web/index.php/directory/viewDirectory']");
	
	
	public void searchDashboard(String sSearchInput) {
		sendKeys(search, sSearchInput);
		boolean isDashboardExist = waitVisibility(searchResult).isDisplayed();
		verify.verify(isDashboardExist, "Search results found", "Search results not found", "Search_directory");
	}

}
