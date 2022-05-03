package resman.qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;

public class utilities extends baseClass {
	configure config;
	
	WebDriver driver = baseClass.getDriver();

	public String takescreenshot_driver(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot screen_shot = (TakesScreenshot) driver;
		File input_source = screen_shot.getScreenshotAs(OutputType.FILE);

		String filePathname = config.properties.getProperty("user.dir") + "\\reports" + testcasename + ".png";
		File output_source = new File(filePathname);
		// file utils
		FileUtils.copyFile(input_source, output_source);

		return filePathname;
	}
}
