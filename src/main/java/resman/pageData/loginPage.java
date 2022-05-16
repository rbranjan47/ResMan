package resman.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resman.qa.baseClass.baseClass;

public class loginPage extends baseClass {

	//WebElements 
	public static By userName = By.id("Username");
	public static By passWord = By.id("Password");
	public static By loginBtn = By.xpath("//button[contains(text(),'Sign in')]");

	// initializing element
	public loginPage() {
		PageFactory.initElements(driver, this);
	}

	// actions
	public static WebElement usernameInput() {
		return driver.findElement(userName);
	}

	public static WebElement passwordInput() {
		return driver.findElement(passWord);
	}

	public static WebElement loginButtonClick() {
		return driver.findElement(loginBtn);
	}

}
