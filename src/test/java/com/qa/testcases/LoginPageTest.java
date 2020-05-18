package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
	private String userName = prop.getProperty("username");
	private String userPassword = prop.getProperty("password");
	
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
	@Test(priority = 1, description = "Login Page Title")
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small");
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verify User Login")
	@Story("Story Name: Verify Login For Registered User")
	@Test(priority = 2, description = "User Login")
	public void loginTest() {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login(userName, userPassword);
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
