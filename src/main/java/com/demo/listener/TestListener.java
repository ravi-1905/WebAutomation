package com.demo.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.extentmanager.ExtentManager;
import com.demo.extentmanager.ExtentTestManager;
import com.demo.test.BaseTest;
import com.demo.utils.Utils;

public class TestListener extends BaseTest implements ITestListener {

	ExtentReports extent;
	ExtentTest test;
	protected String testMethodName;
	protected WebDriver driver;
	private Logger log = LogManager.getLogger(TestListener.class.getName());
	
	//ExtentReports log operation on start
	public void onStart(ITestContext context) {
		log.info("Test Execution has been started");
	}
	
	//ExtentReports log operation on test start
	public void onTestStart(ITestResult result) {
		testMethodName = result.getMethod().getMethodName();
		extent = ExtentManager.createExtentReports();
		ExtentTestManager.startTest(extent, testMethodName);
		test = ExtentTestManager.getTest();
	}

	//ExtentReports log operation for passed tests.
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, testMethodName+" has been Passed");
		log.info(testMethodName+" has been Passed");
	}

	//ExtentReports log operation for failed tests.
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		driver = getDriver();
		
		//ExtentReports log and screenshot operations for failed tests.
		try {
			test.log(Status.FAIL, testMethodName+" has been Failed");
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			test.addScreenCaptureFromPath(Utils.getScreenshotPath(testMethodName, driver), testMethodName);
			log.info(testMethodName+" has been Failed");
		} catch (Exception e) {
			log.info("Unable to take Screenshot.");
			e.printStackTrace();
		}
	}

	//ExtentReports log operation for skipped tests.
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, testMethodName+" has been Skipped");
		log.info(testMethodName+" has been Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	//ExtentReports log operation on finish
	public void onFinish(ITestContext context) {
		log.info("Test Execution has been finished");
		if(extent!=null) {
			extent.flush();
		}
	}
	
}
