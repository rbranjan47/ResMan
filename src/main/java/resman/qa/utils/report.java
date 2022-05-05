package resman.qa.utils;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class report {
	public ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childtest;
	public static String destination;

	/*
	 * [TestMethod] [Description("create extent report header")]
	 */

	public static ExtentTest createTest(String name) {
		test = extent.createTest(name);
		return test;
	}

	/*
	 * [TestMethod] [Description("create extent report sub header")]
	 */

	public static ExtentTest createNode(String name) {
		childtest = test.createNode(name);
		return childtest;
	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest Logger_createDataFrom(ExtentTest Node_Type, String app, String value) {
		Node_Type.log(Status.INFO, "created '" + value + "' from '" + app + "' app.");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerPASS_createDatafrom(ExtentTest Node_Type, String app, String value) {
		Node_Type.log(Status.PASS, "Successfully created  '" + value + "' from '" + app + "' app.");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerFAIL_createDatafrom(ExtentTest Node_Type, String app, String value, WebDriver driver)
			throws Exception {

		Node_Type.log(Status.FAIL, "Unable to Create '" + value + "' from '" + app + "' app.");

		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for Updating data")]
	 */

	public static ExtentTest Logger_UpdateData(ExtentTest Node_Type, String app, String value) {
		Node_Type.log(Status.INFO, "Updated '" + value + "' in '" + app + "' app.");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerPASS_UpdateData(ExtentTest Node_Type, String app, String value) {
		Node_Type.log(Status.PASS, "Succesfully Updated '" + value + "' from '" + app + "' app.");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerFAIL_UpdateData(ExtentTest Node_Type, String app, String value, WebDriver driver)
			throws Exception {
		Node_Type.log(Status.FAIL, "Unable to Update '" + value + "' from '" + app + "' app.");

		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for launching app")]
	 */

	public static ExtentTest Logger_LauchApp(ExtentTest Node_Type, String app) {
		Node_Type.log(Status.INFO, "Launched '" + app + "' app.");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerPASS_LauchApp(ExtentTest Node_Type, String app) {
		Node_Type.log(Status.PASS, "Succesfully Launched '" + app + "' app.");
		return Node_Type;

	}

	public static ExtentTest Logger_Info(ExtentTest Node_Type, String info) {
		Node_Type.log(Status.INFO, info);
		return Node_Type;

	}
	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest LoggerFAIL_LauchApp(ExtentTest Node_Type, String app, WebDriver driver) throws Exception {
		Node_Type.log(Status.FAIL, "Unable to Launch '" + app + "' app.");

		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for Entering data")]
	 */

	public static ExtentTest Logger_EnterData(ExtentTest Node_Type, String locator, String value) {
		Node_Type.log(Status.INFO, "Entered text '" + value + "' in " + locator);
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for creating data")]
	 */

	public static ExtentTest Logger_CreateData(ExtentTest Node_Type, String locator, String value) {
		Node_Type.log(Status.INFO, "Entered text '" + value + "' in " + locator);
		return Node_Type;

	}
	/*
	 * [TestMethod] [Description("logger for clicking a button")]
	 */

	public static ExtentTest Logger_click(ExtentTest Node_Type, String locator) {
		Node_Type.log(Status.INFO, "clicked on '" + locator + " '");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for selecting data")]
	 */

	public static ExtentTest Logger_SelectData(ExtentTest Node_Type, String data, String locator) {
		Node_Type.log(Status.INFO, "Selected '" + data + "' from " + locator);
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for exact value of data")]
	 */

	public static ExtentTest Logger_PrintData(ExtentTest Node_Type, String data, String locator) {
		Node_Type.log(Status.INFO, locator + " is '" + data + "'");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for landing data")]
	 */

	public static ExtentTest Logger_landingPage(ExtentTest Node_Type, String page) {
		Node_Type.log(Status.INFO, "'" + page + "' page Appeared succsessfully");
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger for failure exception in catch block")]
	 */

	public static ExtentTest Logger_ExceptionFail(ExtentTest Node_Type, Exception e) {
		Node_Type.log(Status.FAIL, "Exception " + e);
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger validation")]
	 */

	public static ExtentTest Logger_Validation(ExtentTest Node_Type, String element) {
		Node_Type.log(Status.INFO, "Validating" + element);
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger validation Pass")]
	 */

	public static ExtentTest ValidationPass(ExtentTest Node_Type, String element) {
		Node_Type.log(Status.PASS, "Succcesfully validated the" + element);
		return Node_Type;

	}

	/*
	 * [TestMethod] [Description("logger validation Fail")]
	 */

	public static ExtentTest ValidationFail(ExtentTest Node_Type, String element) {
		Node_Type.log(Status.FAIL, "Unable to Validate the" + element);

		return Node_Type;

	}
}
