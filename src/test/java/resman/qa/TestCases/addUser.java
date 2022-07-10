package resman.qa.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import resman.pageData.loginPage;
import resman.pageData.resmanaddUser;
import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;
import resman.qa.utils.reusableMethods;
import resman.qa.utils.utilities;

public  class addUser extends baseClass {

	utilities utilss;
	loginPage login = new loginPage();


	@Test(groups = "regression")
	public  void verifyAddUser() throws Exception {
		VerifyUsersPage();
		try {
			resmanaddUser.addBtn().click();
			Thread.sleep(5000);
			test.log(Status.PASS, "Clicked Add Access Button");
		} catch (Exception e) {
			test.log(Status.FAIL, "Not clicked Add button...");
			test.log(Status.FAIL, e.getMessage());
		}
		
		try {
			assertTrue(verifyAddUserLists());
			Thread.sleep(5000);
			test.log(Status.PASS, "user " + configure.user + " is selected!");
		} catch (Exception e) {
			test.log(Status.FAIL, "user " + configure.user + " not selected...");
			test.log(Status.FAIL, e.getMessage());
		} /*
			 * finally { resmanaddUser.okBtn().click(); Thread.sleep(5000); }
			 */

		try {
			resmanaddUser.okBtn().click();
			reusableMethods.threadSleep();
			test.log(Status.PASS, "Clicked 'OK' Button");
		} catch (Exception e) {
			test.log(Status.FAIL, "Not Clicked 'OK' Button...");
			test.log(Status.FAIL, e.getMessage());
		}

		try {
			assertTrue(verifyAddedUser());
			Thread.sleep(5000);
			test.log(Status.PASS, "user " + configure.user + " is added for access!");
		} catch (Exception e) {
			test.log(Status.FAIL, "user " + configure.user + " not found in access lists...");
			test.log(Status.FAIL, e.getMessage());
		}
	}

	@AfterTest
	public void closeUp() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}

	public static void VerifyUsersPage() throws InterruptedException {

		loginPage.usernameInput().sendKeys(configure.Username);
		loginPage.passwordInput().sendKeys(configure.Password);
		loginPage.loginButtonClick().click();

		Thread.sleep(5000);

		String homeUrl = configure.HomeURL;
		try {
			assertEquals(driver.getCurrentUrl(), homeUrl);
			test.log(Status.PASS, "Logged in successfully!");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Logged in Fail!");
			test.log(Status.FAIL, e.getMessage());
		}

		if (resmanaddUser.closeAdvisior().isDisplayed()) {
			resmanaddUser.closeAdvisior().click();
		}
		// Admin
		resmanaddUser.adminClick().click();
		// properties
		reusableMethods.jsClick(resmanaddUser.propertiesClick());
		// Getting all properties name
		Thread.sleep(5000);
		String propertiesPageUrl = configure.PropertiesURL;
		try {
			assertEquals(driver.getCurrentUrl(), propertiesPageUrl);
			test.log(Status.PASS, "Navigated to Properties Page");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Navigation to Properties Page Failed...");
			test.log(Status.FAIL, e.getMessage());
		}

		String propertyName = configure.property;
		// getting all properties lists
		driver.findElement(By.xpath("//span[@class='link list page'][contains(text(),'All')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[normalize-space()='" + propertyName + "']")).click();

		Thread.sleep(5000);
		WebElement propertyNameOnPage = driver.findElement(By.id("Name"));
		try {
			assertEquals(configure.property, propertyNameOnPage.getText());
			test.log(Status.PASS, "Selected & Navigated to " + configure.property + " Property");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Navigation to " + configure.property + " failed...");
			test.log(Status.FAIL, e.getMessage());
		}
		reusableMethods.jsClick(resmanaddUser.usersBtn());
		Thread.sleep(5000);
		List<WebElement> userAdded = driver.findElements(By.className("put-name-col"));
		try {
			assertFalse(userAdded.isEmpty());
			test.log(Status.PASS, "Navigated to users section");
		} catch (Exception e) {
			test.log(Status.FAIL, "Navigation to users section failed...");
			test.log(Status.FAIL, e.getMessage());
		}
	}

	public  static boolean verifyAddedUser() {
		// verify added user
		List<WebElement> userAdded = driver.findElements(By.className("put-name-col"));
		for (int i = 0; i < userAdded.size(); i++) {
			String userSelectedLast = configure.user;
			String usergetAdded = userAdded.get(i).getText();
			if (usergetAdded.equalsIgnoreCase(userSelectedLast)) {
				return true;
			}
		}
		return false;
	}

	public static boolean verifyAddUserLists() {
		// verify ass user from user's list
		for (int i = 0; i < resmanaddUser.unSeletectedUsers().size(); i++) {
			String userNeedtoSelect = configure.user;
			if (resmanaddUser.unSeletectedUsers().get(i).getText().equalsIgnoreCase(userNeedtoSelect)) {
				resmanaddUser.unSeletectedUsers().get(i).click();
				return true;
			}
		}
		return false;
	}
}
