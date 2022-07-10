package resman.qa.baseClass;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import resman.qa.utils.report;

public class baseClass extends report {

	public static WebDriver driver = null;
	public static ExtentReports extent_report;
	public static ExtentTest test;

	@BeforeSuite
	public static ExtentReports report() {
		try {

			String log4jConfPath = System.getProperty("user.dir") + "\\src\\main\\java\\resource\\log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);

			// Creating Reports
			String report_path = System.getProperty("user.dir") + "\\test-output\\ResMan_Automation.html";

			// project report location, in HTML extension
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
			extent_report.attachReporter(reporter);
			extent_report.setSystemInfo("ResMan", "windows");

		} catch (Exception e) {
			System.out.println("Extent Report Not Generated:");
		}
		return extent_report;
	}

	@BeforeMethod
	public static void setup() throws Exception {
		System.out.println("Browser Opening...");
		openBrowser();
		openApplication();
	}

	public static WebDriver openBrowser() throws Exception {
		switch (configure.TEST_BROWSER.trim().toUpperCase()) {

		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "IE":
			WebDriverManager.edgedriver().setup();
			InternetExplorerOptions edgeOptions = new InternetExplorerOptions();
			edgeOptions.setAcceptInsecureCerts(true);
			driver = new InternetExplorerDriver(edgeOptions);
			break;

		case "CHROME":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeOptions);
			break;

		default:
			throw new Exception("Incorrect browser set in config file.");
		}
		return driver;
	}

	public static void openApplication() throws InterruptedException {
		driver.get("https://"+configure.APPLICATION_URL+".myresman.com/");
		System.out.println("Successfully launced the ResMan Url");
		driver.manage().window().maximize();
	}
}
