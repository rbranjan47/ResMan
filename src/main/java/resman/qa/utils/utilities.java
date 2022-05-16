package resman.qa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;

public class utilities extends baseClass {
	configure config;

	// Take Screenshot by storing image
	public String takescreenshot_driver(String testcasename, WebDriver driver) throws IOException { 
	  TakesScreenshot screen_shot = (TakesScreenshot) driver; 
	  File input_source = screen_shot.getScreenshotAs(OutputType.FILE);
	  
	  String filePathname = configure.properties.getProperty("user.dir") + "\\reports\\" + testcasename + ".png"; 
	  File output_source = new File(filePathname);
	  FileUtils.copyFile(input_source, output_source);
	  
	  return filePathname; 
	  }

	/*
	 * // ScreenShot base64 
	 **/
	
	 public String screenShot(WebDriver driver) {
		 String screenShotPath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	 return screenShotPath;
	 }
}
