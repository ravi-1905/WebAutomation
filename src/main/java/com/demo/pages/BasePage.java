package com.demo.pages;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public WebDriver driver;
	private WebDriverWait wait;
	public Properties prop;
	private Logger log = LogManager.getLogger(BasePage.class.getName());

	// Initializes the driver instance and invoke the browser
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	}

	public WebElement waitVisibility(By by) {
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (TimeoutException e) {
			log.info("Unable to find on the Element on the given time " + e.getStackTrace());
		}
		return element;
	}

	// Click Method
	public void click(By by) {
		try {
			waitVisibility(by).click();
		} catch (ElementClickInterceptedException exception) {
			log.info("Unable to Click on the Element " + exception.getStackTrace());
		}
	}

	// Write Text
	public void sendKeys(By by, String text) {
		try {
			waitVisibility(by).sendKeys(text);
		} catch (ElementClickInterceptedException exception) {
			log.info("Element not click interaceptable: " + exception.getStackTrace());
		}
	}

	// Get inner Text
	public String getText(By by) {
		String innerText = null;

		try {
			innerText = waitVisibility(by).getText();
		} catch (Exception exception) {
			log.info("Unable to get inner text of given element: " + exception.getStackTrace());
		}
		return innerText;
	}
	
	//Get attribute
	public String getAttribute(By by, String sAttributeName) {
		String attributeValue = null;

		try {
			attributeValue = waitVisibility(by).getAttribute(sAttributeName);
		} catch (Exception exception) {
			log.info("Unable to get attribute: " + sAttributeName + " of given element: " + exception.getStackTrace());
		}
		return attributeValue;
	}
	
	//Select from dropdown menu
	public void selectFromDropdown(By by, String sSelectBy, String sValue) {
		try {
			Select select = new Select(waitVisibility(by));

			switch (sSelectBy) {
			case "Index":
				select.selectByIndex(Integer.parseInt(sValue));
				break;

			case "Value":
				select.selectByValue(sValue);
				break;

			case "VisibleText":
				select.selectByVisibleText(sValue);
				break;

			default:
				break;
			}

		} catch (Exception exception) {
			log.info("Unable to select from dropdown " + exception.getStackTrace());
		}
	}

	//Scroll to element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", waitVisibility(by));
		} catch (Exception exception) {
			log.info("Unable to scroll to given element: " + exception.getStackTrace());
		}
	}

}
