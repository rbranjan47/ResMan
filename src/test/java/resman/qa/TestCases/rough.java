package resman.qa.TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import resman.qa.baseClass.baseClass;

public class rough extends baseClass{

	String consoleText ;
	final static Logger logger = LoggerFactory.getLogger(rough.class);
	@Test
	public void testMethod() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = baseClass.getDriver();
		driver.get("https://regression.myresman.com/");
		driver.findElement(By.id("Username")).sendKeys("qavats");
		driver.findElement(By.id("Password")).sendKeys("qavats");
		driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("CloseAdvisor")).click();
		Actions actions = new Actions(driver);
		WebElement admin = driver.findElement(By.xpath("//a[contains(text(),'Admin')]"));
		actions.moveToElement(admin).build().perform();
		driver.findElement(By.xpath("//a[contains(@href,'#/Property')]")).click();
		System.out.println(fetchAPI());
	}
	
	public String fetchAPI() {
		/*
		DevTools devtool; 
		ChromeDriver driver = (ChromeDriver) baseClass.getDriver();
		//Initializing Developer tools
		devtool = driver.getDevTools();
		devtool.createSession();
		
		devtool.send(Log.enable());
		devtool.addListener(Log.entryAdded(), entries->{
			consoleText = entries.getText();
			consoleLevel = entries.getLevel(); */
		ChromeDriver driver = (ChromeDriver) baseClass.getDriver();
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
		
		List<org.openqa.selenium.logging.LogEntry>logs = entry.getAll();
		
		for(org.openqa.selenium.logging.LogEntry consoleLogs : logs) {
			consoleText = consoleLogs.getMessage();
		}

		return consoleText;
	}
}

