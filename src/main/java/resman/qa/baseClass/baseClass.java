package resman.qa.baseClass;

import java.io.ObjectInputFilter.Config;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class baseClass extends report{
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/*
	 * Sets up browser with base URL for test case execution
	 * 
	 * @throws IOException
	 */

	@BeforeSuite(alwaysRun = true)
	public void report() throws MalformedURLException {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//test-output//ResMan_Automation.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("RoofHUB", "windows");
		sparkReporter.config().setDocumentTitle("RoofHub_Report");
		sparkReporter.config().setTheme(Theme.STANDARD);

	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {
		System.out.println("BeforeMethod");
		openBrowser();

	}

	/*
	 * Get WebDriver instance from ThreadLocal
	 */

	public static WebDriver getDriver() {
		return driver.get();
	}

	/*
	 * Ends WebDriver session
	 */

	@AfterMethod(alwaysRun = true)

	public void appium_server_stop(ITestResult result) throws Exception {

		System.out.println("AfterMethod");

		if (result.getStatus() == ITestResult.FAILURE) {

			test.fail(MarkupHelper.createLabel(result.getName() + "TestCaseFailed", ExtentColor.RED));
			test.fail(result.getThrowable());
			Thread.sleep(4000);

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + "TestCasePasses", ExtentColor.GREEN));

		} else {
			test.skip(MarkupHelper.createLabel(result.getName() + "TestCaseSkipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}

	}

	@AfterSuite(alwaysRun = true)
	public void flush() throws Exception {

		System.out.println("After suite");
		extent.flush();

	}

	/*
	 * [TestMethod] [Description("To setup the browser driver ")]
	 */
	public static WebDriver openBrowser() throws Exception {
		switch (Config.TEST_BROWSER.trim().toUpperCase()) {

		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver.set(new FirefoxDriver(capabilities));
			break;

		case "IE":
			WebDriverManager.edgedriver().setup();
			driver.set(new InternetExplorerDriver());
			break;

		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;

		default:
			throw new Exception("Incorrect browser set in config file.");
		}
		getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return getDriver();
	}



}
