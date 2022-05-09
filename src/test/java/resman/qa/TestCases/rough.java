package resman.qa.TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import resman.qa.baseClass.baseClass;

public class rough extends baseClass{

	String consoleText ;
	@Test
	public void testMethod() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = baseClass.getDriver();
		driver.get("https://qa.myresman.com/");
		driver.findElement(By.id("Username")).sendKeys("avadmin");
		driver.findElement(By.id("Password")).sendKeys("tester");
		driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
		Thread.sleep(10000);
		
		WebElement homePopupClose = driver.findElement(By.id("CloseAdvisor"));
		if(homePopupClose.isDisplayed()) {
			homePopupClose.click();
		}
		
		Actions actions = new Actions(driver);
		WebElement admin = driver.findElement(By.xpath("//a[contains(text(),'Admin')]"));
		
		actions.moveToElement(admin).click();
		
		WebElement properties = driver.findElement(By.xpath("//a[contains(text(),'Properties')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", properties);
		
		
		List<WebElement> propertiesLists = driver.findElements(By.xpath("//*[@id='PropertyList']/./tbody/tr/td[@class='pl-name-col']"));
		for(int i=0; i< propertiesLists.size(); i++) {
			/*
			if(propertiesLists.get(i).getText().equalsIgnoreCase("Aknsha Test Property")) {
				js.executeScript("arguments[0].click()", propertiesLists.get(i));
			
			}
			else {
				System.out.println("no properties found");
			} */
			System.out.println(propertiesLists.get(i).getText());
		}
		Thread.sleep(5000);
		driver.quit();
		
		//scroll
		/*
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.id("UsersLink")).click();
		WebElement addAccessButton = driver.findElement(By.xpath("//span[contains(text(),'Add Access')]"));
		wait.until(ExpectedConditions.elementToBeClickable(addAccessButton));
		addAccessButton.click();
		
		
		System.out.println(fetchAPI());*/
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

