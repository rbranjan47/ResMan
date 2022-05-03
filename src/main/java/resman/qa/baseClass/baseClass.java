package resman.qa.baseClass;

import java.io.File;
import java.io.ObjectInputFilter.Config;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import resman.qa.utils.report;

public class baseClass extends report {

	static ExtentReports extent_report;
	public static ThreadLocal<Object> driver = new ThreadLocal<>();

	/*
	 * Sets up browser with base URL for test case execution
	 * 
	 * @throws IOException
	 */

	@BeforeSuite(alwaysRun = true)
	public static ExtentReports report() throws MalformedURLException {

		// Creating Reports
		String report_path = System.getProperty("user.dir") + "//test-output//ResMan_Automation.html";
		// project report location, in .html extension
		ExtentSparkReporter reporter = new ExtentSparkReporter(report_path);

		// report name
		String reportName = configure.properties.getProperty("reportname");
		reporter.config().setReportName(reportName);

		// document title
		String document_title = configure.properties.getProperty("documenttitle");
		reporter.config().setDocumentTitle(document_title);

		// setting theme
		reporter.config().setTheme(Theme.DARK);

		// setting format of file
		reporter.config().setEncoding("utf-8");

		extent_report = new ExtentReports();
		extent_report.setSystemInfo("ResMan", "windows");
		extent_report.attachReporter(reporter);

		return extent_report;
	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {
		System.out.println("Browser Opening...");
		openBrowser();

	}

	/*
	 * Get WebDriver instance from ThreadLocal
	 */

	public static WebDriver getDriver() {
		return (WebDriver) driver.get();
	}

	/*
	 * Ends WebDriver session
	 */

	/*
	 * @AfterSuite(alwaysRun = true) public void flush() throws Exception {
	 * 
	 * System.out.println("After suite"); extent.flush();
	 * 
	 * }
	 */

	/*
	 * [TestMethod] [Description("To setup the browser driver ")]
	 */
	public static WebDriver openBrowser() throws Exception {
		switch (configure.TEST_BROWSER.trim().toUpperCase()) {

		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setAcceptInsecureCerts(true);
			driver.set(new FirefoxDriver(firefoxOptions));
			break;

		case "IE":
			WebDriverManager.edgedriver().setup();
			driver.set(new InternetExplorerDriver());
			break;

		case "CHROME":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setAcceptInsecureCerts(true);
			driver.set(new ChromeDriver(chromeOptions));
			break;

		default:
			throw new Exception("Incorrect browser set in config file.");
		}
		getDriver().manage().window().maximize();
		return getDriver();
	}

}
