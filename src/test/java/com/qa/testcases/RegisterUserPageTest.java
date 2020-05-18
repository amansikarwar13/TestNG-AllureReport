package com.qa.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.SignUpPage;
import com.qa.util.AllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ AllureListener.class })
public class RegisterUserPageTest extends TestBase {
	SignUpPage signUp;
	HomePage homePage;

	public RegisterUserPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initialization();
		launchURL(driver);
		signUp = new SignUpPage();
		homePage = new HomePage();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify User Registration with Registered user")
	@Story("Story Name: Verify New Registration with existing user")
	@Test(priority = 1, description = "Registration With Existing Details")
	public void createUserWithRegisteredUser() {
		homePage.verifyLinkSignUp();
		homePage.clickLinkSignUp();
		signUp.registerUser("TestData@gmail.com", "TestDataRunTime@gmail.com", "Password!23");
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verify User Registration with blank user")
	@Story("Story Name: Verify New Registration with blank user")
	@Test(priority = 2, description = "Registration With No Details")
	public void createUserWithBlankUser() {
		homePage.verifyLinkSignUp();
		homePage.clickLinkSignUp();
		signUp.registerUser("", "", "Password!23");
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verify User Registration with new user")
	@Story("Story Name: Verify New Registration with new user")
	@Test(priority = 3, description = "Registration With New User")
	public void createUserWithNewUser() {
		homePage.verifyLinkSignUp();
		homePage.clickLinkSignUp();
		SimpleDateFormat zuluTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss", Locale.US);
		Date now = new Date();
		String random = zuluTimeFormat.format(now);
		String UserName = "test" + random.replaceAll("-", "");
		String email = UserName + "@gmail.com";
		signUp.registerNewUser(UserName, email, "Password!23");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
