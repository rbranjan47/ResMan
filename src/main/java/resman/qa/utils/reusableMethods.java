package resman.qa.utils;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resman.qa.baseClass.baseClass;

public class reusableMethods extends baseClass {

	// implicit wait
	public static void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	// Explicit wait
	public static void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Java-script click
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
	}

	// WebDriver wait, element click-able
	public static void webDriverwaitClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Action class
	public static void actionClass(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	// stale-element retry
	public static void staleElementRetry(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// wait.ignoring(StaleElementReferenceException.class)
		// .until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

	// stale element retry 2
	public static boolean retryingFindClick(WebElement element) throws StaleElementReferenceException {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	// Random Strings
	public static String randomStrings() {
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
