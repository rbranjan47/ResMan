package resman.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resman.qa.baseClass.baseClass;

public class resmanaddUser extends baseClass {
	WebElement element;
	WebDriver driver = baseClass.getDriver();
	
	
	public static By admin = By.xpath("//a[contains(text(),'Admin')]");
	public static By properties = By.xpath("//a[contains(text(),'Properties')]");
	
	//initializing element
	public resmanaddUser() {
		PageFactory.initElements(getDriver(), this);
	}
	//actions
	public By adminClick() {
		return admin;
	}
	
	public By propertiesClick() {
		return properties;
	}
}
