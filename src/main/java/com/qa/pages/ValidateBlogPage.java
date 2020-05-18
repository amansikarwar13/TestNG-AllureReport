package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.qa.base.TestBase;
import com.qa.util.AllureListener;

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

	// Initializing the Page Objects:
	public ValidateBlogPage() {
		PageFactory.initElements(driver, this);
	}

	@Step("Creating a New Blog for Title: {0} AboutArticle: {1} WriteArticle: {2} and Tag: {3}")
	public void creatNewBlog(String title, String abtArticle, String article, String tag) {
		lnkNewPost.click();
		edtArticleTitle.sendKeys(title);
		edtAboutArticle.sendKeys(abtArticle);
		edtWriteArticle.sendKeys(article);
		edtEnterTags.sendKeys(tag);
		btnPublishArticle.click();

		strValue = elmtTitleName.getText();
		Assert.assertEquals(title, strValue,
				"Expected article tile is: Demo Title and Title present on page is: " + strValue);

		strValue = elmtArticle.getText();
		Assert.assertEquals(article, strValue,
				"Expected article is: 'This is a new article' and Article present on page is: " + strValue);
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

}
