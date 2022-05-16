package resman.qa.TestCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import resman.pageData.loginPage;
import resman.pageData.resmanremoveAddedUser;
import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;
import resman.qa.utils.utilities;

public class removeUser extends baseClass {
	utilities utilss;
	loginPage login;

	// Constructor to Use Base_class constructor
	public removeUser() {
		super(); // it will call super class constructor

	}

	@Test(groups = "regression")
	public static void navigateToUserPage() throws InterruptedException {
		addUser.VerifyUsersPage();
		addUser.verifyAddedUser();
		removeAddedUser();
	}

	@AfterTest
	public void closeUp() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}

	public static void removeAddedUser() throws InterruptedException {
		// verify remove user
		List<WebElement> userslists = driver.findElements(By.xpath("//table[@id='PropertyUsersTable']/tbody/tr"));
		for (int i = 0; i < userslists.size(); i++) {
			try {
				String userSelectedLast = configure.user;
				String userneedRemove = userslists.get(i).getText();
				if (userneedRemove.contains(userSelectedLast)) {
					userslists.get(i).click();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		resmanremoveAddedUser.removeBtn().click();
		Thread.sleep(5000);
		resmanremoveAddedUser.removeYesbtn().click();
		Thread.sleep(5000);
	}

	public static void checkRemovedUser() throws InterruptedException{
		//checking user lists to verify removed user
		List<WebElement> userslists = driver.findElements(By.xpath("//table[@id='PropertyUsersTable']/tbody/tr"));
		for (int i = 0; i < userslists.size(); i++) {
			try {
				String userSelectedLast = configure.user;
				String userneedRemove = userslists.get(i).getText();
				while(true) {
					if (userneedRemove.contains(userSelectedLast))
				}
			}
			catch(Exception e) {
				
			}
		}
	}
}
