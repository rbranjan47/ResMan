package resman.qa.TestCases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resman.pageData.loginPage;
import resman.pageData.resmanaddUser;
import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;
import resman.qa.utils.utilities;

public class addUser extends baseClass{
	
	utilities utilss;
	loginPage login ;
	resmanaddUser addusers;
	WebDriver driver = baseClass.getDriver();
	Logger log = LogManager.getLogger(addUser.class.getName());
	
	@Test
	public void VerifyAddUser() {
		login = new loginPage();
		
		log.info("open ResMan application");
		extent.createTest("Open ResMan Application");
		login.usernameInput().sendKeys(configure.properties.getProperty("Username"));
		login.passwordInput().sendKeys(configure.properties.getProperty("Password"));
		login.loginButtonClick().click();
		
		driver.findElement(addusers.adminClick());
		
		log.info("Opening properties page ");
		extent.createTest("Opening Properties page");
		driver.findElement(addusers.propertiesClick());
		
		
	}
}
