package com.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.demo.extentmanager.ExtentManager;

public class Utils {

	private static Logger log = LogManager.getLogger(Utils.class.getName());

	public static Properties loadConfig(String filePath) {
		Properties prop = null;
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			log.info("File not given on location: " + filePath);
		} catch (IOException e) {
			log.info("Unale to read the file");
		}
		return prop;
	}

	public static String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = ExtentManager.reportPath + "//ScreenShots//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(path));
		return path;
	}

	public HashMap<String, String> getTestData(String tcid) throws FilloException, IOException {

		HashMap<String, String> data = new HashMap<String, String>();
		String testDataFilePath = loadConfig("").getProperty("testDataPath");

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(testDataFilePath);
		String strQuery = "Select * from Sheet1 where TCID='" + tcid + "'";
		Recordset recordset = connection.executeQuery(strQuery);

		while (recordset.next()) {
			ArrayList<String> columnNames = recordset.getFieldNames();
			for (String name : columnNames) {
				data.put(name, recordset.getField(name));
			}
		}

		recordset.close();
		connection.close();

		return data;

	}
}
