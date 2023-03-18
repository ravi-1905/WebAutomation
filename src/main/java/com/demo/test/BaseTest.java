package com.demo.test;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.demo.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	private Logger log = LogManager.getLogger(BaseTest.class.getName());
	
	@BeforeSuite
    public void beforeSuite() {
		prop = Utils.loadConfig("./src/main/resources/config.properties");
		String browserName = prop.getProperty("browser");
		String appURL = prop.getProperty("app_url");
		int timeOut = Integer.parseInt(prop.getProperty("Implicit_timeout"));

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--no-sandbox");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().browserVersion("109.0.1518.78").forceDownload();
			WebDriverManager.edgedriver().driverVersion("110.0.1587.69").forceDownload();
			driver = new EdgeDriver();
		} else {
			log.info(browserName + " is not a valid browser");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
		driver.manage().window().maximize();
		log.info("Driver is initialized.");

		driver.get(appURL);
    }

    @AfterSuite
    public void afterSuite() {
        if(driver !=null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
	
}
