package resman.qa.TestCases;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import resman.pageData.loginPage;
import resman.pageData.resmanremoveAddedUser;
import resman.qa.baseClass.baseClass;
import resman.qa.baseClass.configure;
import resman.qa.utils.utilities;

public class removeUser extends baseClass {

	@Test(groups = "regression")
	public static void VerifyRemoveUser() throws Exception {
		Thread.sleep(5000);
		addUser.VerifyUsersPage();
		

		try {
			assertTrue(addUser.verifyAddedUser());
			Thread.sleep(5000);
			test.log(Status.PASS, "user " + configure.user + " is added for access!");
		} catch (Exception e) {
			test.log(Status.FAIL, "user " + configure.user + " not found in access lists...");
			test.log(Status.FAIL, e.getMessage());
		}

		try {
			assertTrue(removeAddedUser());
			Thread.sleep(5000);
			test.log(Status.PASS, "user " + configure.user + " selected!");
		} catch (Exception e) {
			test.log(Status.FAIL, "user " + configure.user + " not selected...");
			test.log(Status.FAIL, e.getMessage());
		}

		resmanremoveAddedUser.removeBtn().click();
		Thread.sleep(2000);

		try {
			resmanremoveAddedUser.removeYesbtn().click();
			Thread.sleep(5000);
			test.log(Status.PASS, "Clicked on Remove button!");
		} catch (Exception e) {
			test.log(Status.FAIL, "Not Clicked on Remove Button...");
			test.log(Status.FAIL, e.getMessage());
		}

		try {
			assertTrue(checkRemovedUser());
			test.log(Status.PASS, "user " + configure.user + " is removed from successfully!");
		} catch (Exception e) {
			test.log(Status.FAIL, "user " + configure.user + " not removed Yet...");
			test.log(Status.FAIL, e.getMessage());
		}
	}

	@AfterTest
	public static void closeUp() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	// string Regex
	public static boolean removeAddedUser() {
		// verify remove user
		String removeUserName = configure.user;
		List<WebElement> usersrowlists = driver.findElements(By.xpath("//table[@id='PropertyUsersTable']/tbody/tr"));
		int userRowSize = usersrowlists.size();

		List<WebElement> usercolumnlists = driver
				.findElements(By.xpath("//*[@id='PropertyUsersTable']/tbody/tr[1]/td"));
		int userColumnSize = usercolumnlists.size();

		for (int i = 1; i <= userRowSize; i++) {
			if (driver.findElement(By.xpath("//*[@id='PropertyUsersTable']/tbody/tr[" + i + "]/td[1]")).getText()
					.equalsIgnoreCase(removeUserName)) {
				driver.findElement(
						By.xpath("//*[@id='PropertyUsersTable']/tbody/tr[" + i + "]/td[" + userColumnSize + "]"))
						.click();
				return true;
			}
		}
		return false;
	}

	public static boolean checkRemovedUser() {
		// checking user lists to verify removed user
		List<WebElement> userslists = driver.findElements(By.className("put-name-col"));
		for (int i = 0; i < userslists.size(); i++) {
			String removedUser = configure.user;
			if (userslists.get(i).getText().equalsIgnoreCase(removedUser)) {
				return false;
			}
		}
		return true;
	}
}
