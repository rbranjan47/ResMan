package resman.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resman.qa.baseClass.baseClass;

public class loginPage extends baseClass {
	WebDriver driver = baseClass.getDriver();

	//WebElements 
	public static By userName = By.id("Username");
	public static By passWord = By.id("Password");
	public static By loginBtn = By.xpath("//button[contains(text(),'Sign in')]");

	// initializing element
	public loginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	// actions
	public WebElement usernameInput() {
		return driver.findElement(userName);
	}

	public WebElement passwordInput() {
		return driver.findElement(passWord);
	}

	public WebElement loginButtonClick() {
		return driver.findElement(loginBtn);
	}

}
