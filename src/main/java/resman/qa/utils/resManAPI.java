package resman.qa.utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.devtools.v85.log.model.LogEntry.Level;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import resman.qa.baseClass.baseClass;

public class resManAPI extends baseClass {
	public static String consoleText;
	public static Level consoleLevel;

	public static String fetchAPI() {

		/*
		 * DevTools devtool;
		 * 
		 * ChromeDriver driver = (ChromeDriver) baseClass.getDriver();
		 * 
		 * //Initializing Developer tools devtool = driver.getDevTools();
		 * devtool.createSession();
		 * 
		 * devtool.send(Log.enable());
		 * 
		 * devtool.addListener(Log.entryAdded(), entries->{ consoleText =
		 * entries.getText(); consoleLevel = entries.getLevel(); }); String
		 * consoleLevelStr = consoleLevel +""; return "Logs: " + consoleLevel + "\r\n" +
		 * "Levels: " + consoleLevelStr;
		 */

		driver = (ChromeDriver) driver;
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);

		List<org.openqa.selenium.logging.LogEntry> logs = entry.getAll();

		for (org.openqa.selenium.logging.LogEntry consoleLogs : logs) {
			consoleText = consoleLogs.getMessage();
		}

		return consoleText;
	}
}
