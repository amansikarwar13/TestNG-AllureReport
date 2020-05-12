package com.crm.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.AllureListener;
import com.qa.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})
public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	public WebDriver driver;
	
	final private static Logger LOGGER = LoggerFactory.getLogger(HomePageTest.class);

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initialization();
		launchURL(driver);
		testUtil = new TestUtil();
		homePage = new HomePage();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Login Page Title")
	@Story("Story Name: To Check Login Page Title")
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Conduit", "Home page title not matched");
	}

	@Test(priority = 2)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Home Link")
	@Story("Story Name: Verifying Home Link")
	public void verifyHomeLink() {
		Assert.assertTrue(homePage.verifyLinkHome(), "Home link is not present on Page");
	}

	@Test(priority = 3)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Sign In Link")
	@Story("Story Name: Verifying Sign In Link")
	public void verifyLoginLink() {
		Assert.assertTrue(homePage.verifyLinkSignIn(), "Sign In link is not present on Page");
	}

	@Test(priority = 4)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Sign Up Link")
	@Story("Story Name: Verifying Sign Up Link")
	public void verifySignUpLink() {
		Assert.assertTrue(homePage.verifyLinkSignUp(), "Sign Up Link is not present on Page");
	}

	@Test(priority = 5)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Tags on Home Page")
	@Story("Story Name: Verifying Tags on Home Page")
	public void verifyTagsOnPage() {
		Assert.assertTrue(homePage.returnListOfPopularTags().size() < 1, "No Popular Tags present on home page");
		LOGGER.info("Tags present on Home Page Are: ");
		for (WebElement strTag : homePage.returnListOfPopularTags()) {
			LOGGER.info(strTag.getText());
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
