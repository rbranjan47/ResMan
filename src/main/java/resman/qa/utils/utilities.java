package resman.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;

public class utilities extends baseClass {
	configure config;

	WebDriver driver = baseClass.getDriver();

	/*
	 * Take Screenshot by storing image public String takescreenshot_driver(String
	 * testcasename, WebDriver driver) throws IOException { TakesScreenshot
	 * screen_shot = (TakesScreenshot) driver; File input_source =
	 * screen_shot.getScreenshotAs(OutputType.FILE);
	 * 
	 * String filePathname = configure.properties.getProperty("user.dir") +
	 * "\\reports" + testcasename + ".png"; File output_source = new
	 * File(filePathname); // file Utils FileUtils.copyFile(input_source,
	 * output_source);
	 * 
	 * return filePathname; }
	 */

	// ScreenShot base64
	public String screenShot(WebDriver getDriver) {
		String screenShotPath = ((TakesScreenshot) getDriver).getScreenshotAs(OutputType.BASE64);
		return screenShotPath;
	}

	// jsClick
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
	}
}
