package resman.qa.TestCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import resman.pageData.loginPage;
import resman.pageData.resmanaddUser;
import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;
import resman.qa.utils.reusableMethods;
import resman.qa.utils.utilities;

public class addUser extends baseClass {

	utilities utilss;
	loginPage login;

	// Constructor to Use Base_class constructor
	public addUser() {
		super(); // it will call super class constructor

	}

	@Test(groups = "regression")
	public static void verifyAddUser() throws InterruptedException {
		VerifyUsersPage();
		resmanaddUser.addBtn().click();
		Thread.sleep(5000);

		verifyAddUserLists();
		Thread.sleep(5000);
		// resmanaddUser.selectUser().click();
		resmanaddUser.okBtn().click();
		Thread.sleep(5000);
		verifyAddedUser();
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

		Thread.sleep(10000);

		/*
		 * String url = "https://regression.myresman.com/#/BoardRoom?al=true"; try {
		 * assertEquals(driver.getCurrentUrl(), url); test.log(Status.PASS,
		 * "Logged in successfully!"); } catch (AssertionError e) {
		 * test.log(Status.FAIL, "Logged in Fail!"); test.log(Status.FAIL,
		 * e.getMessage()); }
		 */
		if (resmanaddUser.closeAdvisior().isDisplayed()) {
			resmanaddUser.closeAdvisior().click();
		}
		// Admin
		// reusableMethods.actionClass(resmanaddUser.adminClick());
		resmanaddUser.adminClick().click();
		// properties
		reusableMethods.jsClick(resmanaddUser.propertiesClick());
		// Getting all properties name
		Thread.sleep(5000);
		// reusableMethods.webDriverwaitVisible(resmanaddUser.platform());
		String propertyName = configure.property;
		/*
		 * while (true) { if (resmanaddUser.propertiesNames().contains(propertyName)) {
		 * break; } else { List<WebElement> nextButton =
		 * driver.findElements(By.xpath("//div[@id='PageLinks']/span")); for (int j = 2;
		 * j <= nextButton.size(); j++) {
		 * driver.findElement(By.xpath("//div[@id='PageLinks']/span[" + j +
		 * "]")).click(); Thread.sleep(5000); } } }
		 */
		// getting all properties lists
		driver.findElement(By.xpath("//span[@class='link list page'][contains(text(),'All')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[normalize-space()='" + propertyName + "']")).click();

		Thread.sleep(5000);
		reusableMethods.jsClick(resmanaddUser.usersBtn());
		Thread.sleep(5000);

	}

	public static void verifyAddedUser() {
		// verify added user
		List<WebElement> userAdded = driver.findElements(By.className("put-name-col"));
		for (int i = 1; i < userAdded.size(); i++) {
			try {
				String userSelectedLast = configure.user;
				String usergetAdded = userAdded.get(i).getText();
				if (usergetAdded.equalsIgnoreCase(userSelectedLast)) {
					assertEquals(userSelectedLast, usergetAdded);
					System.out.println("Selected user present in users lists");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public static void verifyAddUserLists() throws InterruptedException {
		// verify ass user from user's list
		for (int i = 0; i < resmanaddUser.unSeletectedUsers().size(); i++) {
			try {
				String userNeedtoSelect = configure.user;
				if (resmanaddUser.unSeletectedUsers().get(i).getText().equalsIgnoreCase(userNeedtoSelect)) {
					resmanaddUser.unSeletectedUsers().get(i).click();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
