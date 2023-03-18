/**
Ravi Kumar
WebAutomation
*/
package com.demo.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.demo.extentmanager.ExtentTestManager;
import com.demo.test.BaseTest;

public class Verification extends BaseTest {
	WebDriver driver;
	private Logger log = LogManager.getLogger(Verification.class.getName());

	public Verification(WebDriver driver) {
		this.driver = driver;
	}

	public void verify(boolean status, String sSuccessMsg, String sFailureMsg, String sScreenShotName) {
		if (status) {
			log.info(sSuccessMsg);
			try {
				String path = Utils.getScreenshotPath(sScreenShotName, driver);
				ExtentTestManager.getTest().log(Status.PASS, sSuccessMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				Assert.assertTrue(status);
			} catch (Exception e) {
				log.info(e.getStackTrace());
			}

		} else {
			log.info(sFailureMsg);
			try {
				String path = Utils.getScreenshotPath(sScreenShotName, driver);
				ExtentTestManager.getTest().log(Status.FAIL, sSuccessMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				Assert.assertFalse(status);
			} catch (IOException e) {
				log.info(e.getStackTrace());
			}

		}
	}
}
