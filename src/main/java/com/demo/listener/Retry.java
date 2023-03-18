package com.demo.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 0; // Run the failed test 2 times

	@Override
	public boolean retry(ITestResult iTestResult) {
		boolean retryStatus = false;
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxTry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed and take base64Screenshot
//				extendReportsFailOperations(iTestResult); // ExtentReports fail operations
				retryStatus = true;
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return retryStatus;
	}
}