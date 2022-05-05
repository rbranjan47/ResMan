package resman.qa.utils;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resman.qa.baseClass.baseClass;

public class reusableMethods extends baseClass{
	WebDriver driver = baseClass.getDriver();

	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
	}

	public void webDriverwait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public String randomStrings() {
		String randomNames = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomBuilder = new StringBuilder();
        Random rnd = new Random();
        while (randomBuilder.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * randomNames.length());
            randomBuilder.append(randomNames.charAt(index));
        }
        String templateRandomNames = randomBuilder.toString();
        
        return templateRandomNames;
	}
}
