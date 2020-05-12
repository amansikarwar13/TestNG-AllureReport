package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.ExtentReportListener.ExtentReporterNG;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.AllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ AllureListener.class })
public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ExtentReporterNG erng;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initialization();
		launchURL(driver);
		loginPage = new LoginPage();
		homePage = new HomePage();
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Login Page Title")
	@Story("Story Name: To Check Login Page Title")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small");
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verify User Login")
	@Story("Story Name: Verify Login For Registered User")
	@Test(priority = 2)
	public void loginTest() {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login("testdata1@gmail.com", "Password@123");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
