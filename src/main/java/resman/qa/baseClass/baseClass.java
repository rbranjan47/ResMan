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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/*
	 * Sets up browser with base URL for test case execution
	 * 
	 * @throws IOException
	 */

	@BeforeSuite(alwaysRun = true)
	public void report() throws MalformedURLException {
		System.out.println("BeforeSuite");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//RoofHub_Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("RoofHUB", "windows");
		htmlReporter.config().setDocumentTitle("RoofHub_Report");
		// htmlReporter.config().setReportName("RoofHub_APPIUM");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {
		System.out.println("BeforeMethod");
		openBrowser();
		openApplication();

	}

	/*
	 * Get WebDriver instance from ThreadLocal
	 * 
	 * @return
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
			// System.setProperty("webdriver.gecko.driver",
			// "C://Users//Thinksysuser//eclipse-workspace//RoofHubTest//RoofHubTest//geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver.set(new FirefoxDriver(capabilities));
			break;

		case "IE":
			// System.setProperty("webdriver.chrome.driver",
			// "C://Users//Thinksysuser//eclipse-workspace//RoofHubTest//RoofHubTest//IEDriverServer.exe");
			WebDriverManager.edgedriver().setup();
			driver.set(new InternetExplorerDriver());
			break;

		case "CHROME":

			// System.setProperty("webdriver.chrome.driver","C://Users//Thinksysuser//eclipse-workspace//RoofHubTest//RoofHubTest//chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;

		default:
			throw new Exception("Incorrect browser set in config file.");
		}
		getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return getDriver();
	}

	/*
	 * [TestMethod] [Description("To Open the application")]
	 */
	public static void openApplication() {
		getDriver().get(Config.APPLICATION_URL);
		System.out.println("Successfully launced the url");
		getDriver().manage().window().maximize();
	}

}
