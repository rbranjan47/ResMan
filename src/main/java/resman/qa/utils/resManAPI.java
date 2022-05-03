package resman.qa.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.devtools.v85.log.model.LogEntry.Level;

import resman.qa.baseClass.baseClass;

public class resManAPI extends baseClass {
	String consoleText ;
	Level consoleLevel;
	
	public String fetchAPI() {
		
		DevTools devtool; 
		
		ChromeDriver driver = (ChromeDriver) baseClass.getDriver();
		
		//Initializing Developer tools
		devtool = driver.getDevTools();
		devtool.createSession();
		
		devtool.send(Log.enable());
		
		devtool.addListener(Log.entryAdded(), entries->{
			consoleText = entries.getText();
			consoleLevel = entries.getLevel();
		});
		String consoleLevelStr = consoleLevel +"";
		return "Logs: " + consoleLevel + "\r\n" + "Levels: " + consoleLevelStr; 
	}
}
