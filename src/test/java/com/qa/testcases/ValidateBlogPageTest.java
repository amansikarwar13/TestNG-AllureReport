package com.qa.testcases;

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
	ValidateBlogPage bloggerPage;
	CommonMethod cm;
	private String userName = prop.getProperty("username");
	private String userPassword = prop.getProperty("password");
	
	public ValidateBlogPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initialization();

		launchURL(driver);
		loginPage = new LoginPage();
		homePage = new HomePage();
		bloggerPage = new ValidateBlogPage();
		cm = new CommonMethod();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: New Post For Existing User")
	@Story("Story Name: Verify New Post")
	@Test(priority = 1, description = "Creating A New Blog And Validating It on Global Feed Page")
	public void ValidateArticleOnGlobalFeed() throws InterruptedException {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login(userName, userPassword);

		String randomUsingTimeStamp = cm.generateRandomString();
		String article = "test" + randomUsingTimeStamp;
		String title = "Title " + article;
		String abtArticle = "About " + article;

		bloggerPage.clickLinkNewPost();
		bloggerPage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();

		homePage.validatingArticleTitlePresentOnPage(title, false);
//		cm.pageContainsText(title, "15", true);

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: New Blog For Failed Scenario")
	@Story("Story Name: Verify New Post For Failed Scearnio")
	@Test(priority = 2, description = "New Post For Failed Scenario")
	public void ValidateArticleOnGlobalFeed2() throws InterruptedException {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login(userName, userPassword);

		// Creating Title Name:
		String randomUsingTimeStamp = cm.generateRandomString();
		String article = "test" + randomUsingTimeStamp;
		String title = "Title " + article;
		String abtArticle = "About " + article;

		bloggerPage.clickLinkNewPost();
		bloggerPage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();
		homePage.validatingArticleTitlePresentOnPage("HelloNoArticlePresent", false);
//		cm.pageContainsText("HelloNoArticlePresent", "15", true);

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Updating Blog For Existing User")
	@Story("Story Name: Verify Updated Post")
	@Test(priority = 3, description = "Updating A Newly Created Blog And Validating It on Global Feed Page")
	public void ValidateArticleOnGlobalFeedAfterEditingArticle() throws InterruptedException {
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login(userName, userPassword);
		
		String randomUsingTimeStamp = cm.generateRandomString();
		String article = "test" + randomUsingTimeStamp;
		String title = "Title " + article;
		String abtArticle = "About " + article;
		
		bloggerPage.clickLinkNewPost();
		bloggerPage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();

		homePage.validatingArticleTitlePresentOnPage(title, true);
		bloggerPage.clickEditArticle();

		String newTitle = "UpdateTitle: " + randomUsingTimeStamp;
		bloggerPage.updateBlog(newTitle, "", "", "");

		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();

		homePage.validatingArticleTitlePresentOnPage(newTitle, true);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Deleting Blog after Creating It")
	@Story("Story Name: Verify Deleted Post")
	@Test(priority = 4, description = "Deleting Blog after Creating It And Validating On Global Feed Page")
	public void validateDeletedArticleNotPresentOnGlobalFeed() throws InterruptedException {
		
		//Login Using Existing User
		homePage.verifyLinkSignIn();
		homePage.clickLinkSignIn();
		loginPage.login(userName, userPassword);

		String randomUsingTimeStamp = cm.generateRandomString();
		String article = "test" + randomUsingTimeStamp;
		String title = "Title " + article;
		String abtArticle = "About " + article;
		
		//Creating a New Blog
		bloggerPage.clickLinkNewPost();
		bloggerPage.creatNewBlog(title, abtArticle, article, "NEW_TAG");
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();
		
		//Validating the Blog on Global Feed
		homePage.validatingArticleTitlePresentOnPage(title, true);
		
		//Deleting the Blog
		bloggerPage.clickDeleteArticle();
		homePage.clickLinkHome();
		homePage.clickLinkGlobalFeed();
		
		//Post Deleting Blog Validating on Global Feed
		homePage.validatingArticleNotPresentOnPage(title);
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
