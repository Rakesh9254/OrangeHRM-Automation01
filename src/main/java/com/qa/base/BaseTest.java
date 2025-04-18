package com.qa.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.util.ConfigReader;
import com.qa.util.ExtentReportManager;
import org.testng.ITestResult;
import com.qa.util.ScreenshotUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setUpReport() {
		extent = ExtentReportManager.initReport();
	}

	@BeforeMethod
	public void setUp() {
		prop = ConfigReader.initProp();
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		// (Optional) Add Firefox, Edge support here

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
			test.fail("Test Failed. Screenshot attached.").addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		}

		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void flushReport() {
		extent.flush();
	}
}
