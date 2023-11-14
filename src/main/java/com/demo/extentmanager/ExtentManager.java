/**
Ravi Kumar
WebAutomation
*/
package com.demo.extentmanager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static Logger log = LogManager.getLogger(ExtentManager.class.getName());
	public static final ExtentReports extentReports = new ExtentReports();
	static DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	public static String reportPath = System.getProperty("user.dir") + "//reports//" + dateFormat.format(new Date());

	public synchronized static ExtentReports createExtentReports() {
		String path = reportPath + "//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		try {
			extentReports.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.info("Unable to capture hostname.");
		}

		return extentReports;
	}
	
}
