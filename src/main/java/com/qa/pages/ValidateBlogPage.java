package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.qa.base.TestBase;
import com.qa.util.AllureListener;
import com.qa.util.TestUtil;

import io.qameta.allure.Step;

@Listeners({ AllureListener.class })
public class ValidateBlogPage extends TestBase {

	private static String strValue;

	@FindBy(xpath = "//*[contains(text(),'New Post')]")
	WebElement lnkNewPost;

	@FindBy(xpath = "//button[text()='Publish Article']")
	WebElement btnPublishArticle;

	@FindBy(xpath = "//input[@placeholder='Article Title']")
	WebElement edtArticleTitle;

	@FindBy(xpath = "//input[contains(@placeholder,'this article about?')]")
	WebElement edtAboutArticle;

	@FindBy(xpath = "//*[contains(@placeholder,'Write your article')]")
	WebElement edtWriteArticle;

	@FindBy(xpath = "//*[contains(@placeholder,'Enter tags')]")
	WebElement edtEnterTags;

	@FindBy(xpath = "//h1[ancestor::div[@class='article-page']]")
	WebElement elmtTitleName;

	@FindBy(xpath = "//p[ancestor::div[@class='container page']]")
	WebElement elmtArticle;

	@FindBy(xpath = "//button[text()='Post Comment']")
	WebElement btnPostComment;

	@FindBy(xpath = "//a[contains(text(),'Edit Article')]")
	WebElement lnkEditArticle;
	
	@FindBy(xpath = "//button[contains(text(),'Delete Article')]")
	WebElement btnDeleteArticle;

	// Initializing the Page Objects:
	public ValidateBlogPage() {
		PageFactory.initElements(driver, this);
	}

	@Step("Clicking on Edit Article Link")
	public void clickEditArticle() {
		lnkEditArticle.click();
	}
	
	@Step("Clicking on Delete Article Button")
	public void clickDeleteArticle() {
		btnDeleteArticle.click();
	}

	@Step("Clicking on Link New Post")
	public void clickLinkNewPost() {
		lnkNewPost.click();
	}

	@Step("Creating a New Blog for Title: {0} AboutArticle: {1} WriteArticle: {2} and Tag: {3}")
	public void creatNewBlog(String title, String abtArticle, String article, String tag) {

		edtArticleTitle.sendKeys(title);
		edtAboutArticle.sendKeys(abtArticle);
		edtWriteArticle.sendKeys(article);
		edtEnterTags.sendKeys(tag);

		btnPublishArticle.click();

		strValue = elmtTitleName.getText();
		Assert.assertEquals(title, strValue,
				"Expected article tile is: " + title + " but Title present on page is: " + strValue);

		strValue = elmtArticle.getText();
		Assert.assertEquals(article, strValue,
				"Expected article is: " + article + " but Article present on page is: " + strValue);
	}

	@Step("Validating Article on Global Feed")
	public void validatingArticle(String UserName, String Password) {

		lnkNewPost.click();
		edtArticleTitle.sendKeys("Demo Title");
		edtAboutArticle.sendKeys("About Article");
		edtWriteArticle.sendKeys("This is a new article");
		edtEnterTags.sendKeys("Demo");
		btnPublishArticle.click();

		strValue = elmtTitleName.getText();
		Assert.assertEquals("Demo Title", strValue,
				"Expected article tile is: Demo Title and Title present on page is: " + strValue);

		strValue = elmtArticle.getText();
		Assert.assertEquals("This is a new article", strValue,
				"Expected article is: 'This is a new article' and Article present on page is: " + strValue);
	}

	@Step("Updating Existing blog with Title: {0} AboutArticle: {1} WriteArticle: {2} and Tag: {3}")
	public void updateBlog(String title, String abtArticle, String article, String tag) throws InterruptedException {
		
		Thread.sleep(5000);
		new Actions(driver).click(edtArticleTitle)
        .pause(200).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
        .pause(200).sendKeys(Keys.BACK_SPACE)
        .pause(200).sendKeys(title).perform();
		Thread.sleep(5000);
		
//		edtAboutArticle.sendKeys(abtArticle);
//		edtWriteArticle.sendKeys(article);
//		edtEnterTags.sendKeys(tag);
//		Thread.sleep(5000);
		btnPublishArticle.click();

		strValue = elmtTitleName.getText();
		Assert.assertEquals(strValue, title);
	}
}
