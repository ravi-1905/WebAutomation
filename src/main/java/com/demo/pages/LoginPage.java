/**
Ravi Kumar
WebAutomation
*/
package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.utils.Verification;

public class LoginPage extends BasePage {
	
	Verification verify;
	public LoginPage(WebDriver driver) {
		super(driver);
		verify = new Verification(driver);
	}

	/** Home page Web Elements **/
	By userName = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By loginBtn = By.xpath("//button[@type='submit']");
	By profilePicture = By.xpath("//img[@alt='profile picture']");
	
	
	/** Home page Methods **/
	public LoginPage login(String sUserName, String sPassword) {
		sendKeys(userName, sUserName);
		sendKeys(password, sPassword);
		click(loginBtn);
		boolean isProfileDisplayed = waitVisibility(profilePicture).isDisplayed();
		verify.verify(isProfileDisplayed, "Login sucessfull",  "Login Failed", "Login");
		return new LoginPage(driver);
	}
}
