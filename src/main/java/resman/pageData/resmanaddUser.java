package resman.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resman.qa.baseClass.baseClass;

public class resmanaddUser extends baseClass {
	WebDriver driver = baseClass.getDriver();
	
	
	public static By assignLeaveButton = By.xpath("//span[contains(text(),'Assign Leave')]");
}
