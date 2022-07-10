package resman.pageData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;

public class resmanaddUser extends baseClass {

	public static By admin = By.xpath("//a[contains(text(),'Admin')]");
	public static By properties = By.xpath("//a[contains(text(),'Properties')]");
	public static By closeAdvisor = By.id("CloseAdvisor");
	public static By propertiesName = By.xpath("//a[contains(@href, '#/Property/Detail')]");
	public static By allBtn = By.xpath("//span[@class='link list page'][contains(text(),'All')]");
	public static By usesbtn = By.xpath("//a[@id='UsersLink']");
	public static By addbtn = By.id("btnaddaccess");
	public static By unSelected = By.xpath("//td[@class='aus-user-col']");
	public static By okbtn = By.xpath("//span[contains(text(),'OK')]");
	public static By selectuser = By.xpath("//label[contains(text(),'" + configure.user + "')]");

	// initializing element
	public resmanaddUser() {
		PageFactory.initElements(driver, this);
	}

	// actions
	public static WebElement closeAdvisior() {
		return driver.findElement(closeAdvisor);
	}

	public static WebElement adminClick() {
		return driver.findElement(admin);
	}

	public static WebElement propertiesClick() {
		return driver.findElement(properties);
	}

	public static List<WebElement> propertiesNames() {
		List<WebElement> property = driver.findElements(propertiesName);
		return property;
	}

	public static WebElement allButton() {
		return driver.findElement(allBtn);
	}

	public static WebElement usersBtn() {
		return driver.findElement(usesbtn);
	}

	public static WebElement addBtn() {
		return driver.findElement(addbtn);
	}

	public static WebElement selectUser() {
		return driver.findElement(selectuser);
	}

	public static WebElement okBtn() {
		return driver.findElement(okbtn);
	}

	public static List<WebElement> unSeletectedUsers() {
		List<WebElement> unselectedusers = driver.findElements(unSelected);
		return unselectedusers;
	}
}
