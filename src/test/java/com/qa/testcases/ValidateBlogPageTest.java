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
import com.qa.pages.LoginPage;
import com.qa.pages.ValidateBlogPage;
import com.qa.util.AllureListener;
import com.qa.util.CommonMethod;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ AllureListener.class })
public class ValidateBlogPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ValidateBlogPage welcomePage;
	CommonMethod cm;

	public ValidateBlogPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initialization();
		launchURL(driver);
		loginPage = new LoginPage();
		homePage = new HomePage();
		welcomePage = new ValidateBlogPage();
		cm = new CommonMethod();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: New Post For Existing User")
	@Story("Story Name: Verify New Post")
	@Test(priority = 1, description = "Creating A New Blog And Validating It on Global Feed Page")
	public void ValidateArticleOnGlobalFeed() throws InterruptedException {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login("testdata1@gmail.com", "Password@123");

		// Creating Title Name:
		SimpleDateFormat zuluTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss", Locale.US);
		Date now = new Date();
		String random = zuluTimeFormat.format(now);
		String titleID = "test" + random.replaceAll("-", "");
		String title = "Title" + titleID;
		String abtArticle = "About" + titleID;
		String article = titleID;

		welcomePage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();

		cm.pageContainsText(title, "15", true);

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: New Blog For Failed Scenario")
	@Story("Story Name: Verify New Post For Failed Scearnio")
	@Test(priority = 2, description = "New Post For Failed Scenario")
	public void ValidateArticleOnGlobalFeed2() throws InterruptedException {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login("testdata1@gmail.com", "Password@123");

		// Creating Title Name:
		SimpleDateFormat zuluTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss", Locale.US);
		Date now = new Date();
		String random = zuluTimeFormat.format(now);
		String titleID = "test" + random.replaceAll("-", "");
		String title = "Title" + titleID;
		String abtArticle = "About" + titleID;
		String article = titleID;

		welcomePage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();

		cm.pageContainsText("HelloNoArticlePresent", "15", true);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
