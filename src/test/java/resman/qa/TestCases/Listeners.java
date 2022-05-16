package resman.qa.TestCases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import resman.qa.baseClass.baseClass;
import resman.qa.utils.resManAPI;
import resman.qa.utils.utilities;

public class Listeners extends baseClass implements ITestListener, IAnnotationTransformer, IReporter {

	utilities utils = new utilities();
	resManAPI consoleLogs;
	Markup markup;

	// calling the extent reports method
	ExtentReports extent_report = baseClass.report();

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	// IT WILL EXECUTE ON TEST START
	@Override
	public void onTestStart(ITestResult result) {
		// extent
		test = extent_report.createTest(result.getTestClass().getName() + "->" + result.getMethod().getMethodName());
		extentTest.set(test);
		// log4j
		Logs.infoMethod(result.getMethod().getMethodName() + " has passed");
	}

	// IT WILL ON TEST SUCCESS
	@Override
	public void onTestSuccess(ITestResult result) {
		// extent report
		String testcaseMethod_Name = result.getMethod().getMethodName();
		String log_text = "<b> Test Case " + testcaseMethod_Name + "  has passed!</b>";

		markup = MarkupHelper.createLabel(log_text, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, markup);

		// log4j
		Logs.infoMethod(testcaseMethod_Name + " has passed");
		Logs.infoMethod(result.getMethod().getDescription());
	}

	// IT WILL EXECUTE TEST FAILURE
	@Override
	public void onTestFailure(ITestResult result) {
		// WebDriver driver = null;
		String throwable_message = Arrays.deepToString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color='red'>" + "Exception Occured!" + "</font></b></summary>"
				+ throwable_message.replaceAll(",", "<br>") + "</details> \n");
		// extentTest.get().fail(consoleLogs.)

		String testcaseMethod_Name = result.getMethod().getMethodName();
		// log fail test
		String log_text = "<b> Test Case " + testcaseMethod_Name + "  has faild</b>";

		markup = MarkupHelper.createLabel(log_text, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, markup);

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {

			e1.printStackTrace();
		}
		try {
			// extentTest.get().addScreenCaptureFromPath(utils.takescreenshot_driver(testcaseMethod_Name,driver),
			// testcaseMethod_Name);
			extentTest.get().fail("", MediaEntityBuilder
					.createScreenCaptureFromBase64String("data:image/png;base64," + utils.screenShot(driver)).build());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		// log4j
		Logs.fatalMethod("Failed, Exception: " + throwable_message);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testcaseMethod_Name = result.getMethod().getMethodName();
		String log_text = "<b> Test Case " + testcaseMethod_Name + "  has Skipped!</b>";

		markup = MarkupHelper.createLabel(log_text, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, markup);

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {

			e1.printStackTrace();
		}
		// taking screenshot
		try {
			// extentTest.get().addScreenCaptureFromPath(utils.takescreenshot_driver(testcaseMethod_Name,driver),
			// testcaseMethod_Name);
			extentTest.get().fail("", MediaEntityBuilder
					.createScreenCaptureFromBase64String("data:image/png;base64," + utils.screenShot(driver)).build());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	// IT WILL EXECUTE BY GIVING RESULT IN PERCENTAGE WITH SUCCESS PERCENT
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		Logs.infoMethod("============Started:" + context.getName() + "============");
	}

	@Override
	public void onFinish(ITestContext context) {
		// to notify extent, reporting is completed
		extent_report.flush();
		// log4j
		Logs.infoMethod("============Finished:" + context.getName() + "============");
	}

	// re-try analyzer
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(reTryAnalyzer.class);
	}

	// Generate reporter
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		ISuite suite = suites.get(0);
		// mapping, to get all methods
		Map<String, Collection<ITestNGMethod>> methodByGroups = suite.getMethodsByGroups();
		// mapping to gets test results
		Map<String, ISuiteResult> testByGroups = suite.getResults();

		// looping in the keys
		for (String key : testByGroups.keySet()) {
			System.out.println("key:" + key + "Value:" + testByGroups.get(key));
		}

		Collection<ISuiteResult> suiteResults = testByGroups.values();

		ISuiteResult suiteResult = suiteResults.iterator().next();
		ITestContext testContext = suiteResult.getTestContext();
		Collection<ITestNGMethod> perfMethods = methodByGroups.get("regression");
		IResultMap failedTests = testContext.getFailedTests();
		for (ITestNGMethod perfMethod : perfMethods) {
			Set<ITestResult> testResultSet = failedTests.getResults(perfMethod);
			for (ITestResult testResult : testResultSet) {
				System.out.println("Test " + testResult.getName() + " failed, error " + testResult.getThrowable());
			}
		}
		IResultMap passedTests = testContext.getPassedTests();
		for (ITestNGMethod perfMethod : perfMethods) {
			Set<ITestResult> testResultSet = passedTests.getResults(perfMethod);
			for (ITestResult testResult : testResultSet) {
				System.out.println("Test " + testResult.getName() + " passed, time took "
						+ (testResult.getEndMillis() - testResult.getStartMillis()));
			}
		}

	}

}
