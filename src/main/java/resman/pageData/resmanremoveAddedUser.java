package resman.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resman.qa.baseClass.baseClass;

public class resmanremoveAddedUser extends baseClass {

	public static By removebtn = By.xpath("//span[contains(text(),'Remove Access')]");
	public static By yesbtn = By.xpath("//span[contains(text(),'Yes')]");

	// initializing element
	public resmanremoveAddedUser() {
		PageFactory.initElements(driver, this);
	}

	// actions
	public static WebElement removeBtn() {
		return driver.findElement(removebtn);
	}

	public static WebElement removeYesbtn() {
		return driver.findElement(yesbtn);
	}
}
