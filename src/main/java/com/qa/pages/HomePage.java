package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[text()='Home']")
	@CacheLookup
	WebElement lnkHome;

	@FindBy(xpath = "//a[text()='Sign in']")
	WebElement lnkSignIn;

	@FindBy(xpath = "//a[text()='Sign up']")
	WebElement lnkSignUp;

	@FindBy(xpath = "//a[parent::div[preceding-sibling::*[text()='Popular Tags']]]")
	List<WebElement> lstOfTags;

	@FindBy(xpath = "//a[i[@class='ion-social-github']]")
	WebElement lnkGitHub;

	@FindBy(xpath = "//a[text()='Your Feed']")
	WebElement lnkYourFeed;

	@FindBy(xpath = "//a[text()='Global Feed']")
	WebElement lnkGlobalFeed;

	@FindBy(xpath = "//h1[ancestor::div[@class='article-preview']]")
	List<WebElement> lstArticleTitle;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@Step("Verifying Title For Home Page")
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	@Step("Getting Home Link On Home Page")
	public boolean verifyLinkHome() {
		return lnkHome.isDisplayed() && lnkHome.isEnabled();
	}

	@Step("Clicking Home Page Link")
	public void clickLinkHome() {
		lnkHome.click();
	}

	@Step("Verifying Sign In Link On Home Page")
	public boolean verifyLinkSignIn() {
		return lnkSignIn.isDisplayed() && lnkSignIn.isEnabled();
	}

	@Step("Clicking Sign In Link On Home Page")
	public void clickLinkSignIn() {
		lnkSignIn.click();
	}

	@Step("Clicking Sign Up Link On Home Page")
	public void clickLinkSignUp() {
		lnkSignUp.click();
	}

	@Step("Verifying Sign Up Link On Home Page")
	public boolean verifyLinkSignUp() {
		return lnkSignUp.isDisplayed() && lnkSignUp.isEnabled();
	}

	@Step("Returning List Of All Popular Tags Present On Home Page")
	public List<WebElement> returnListOfPopularTags() {
		return lstOfTags;
	}

	@Step("Clicking Your Feed Link On Home Page")
	public void clickLinkYourFeed() {
		lnkYourFeed.click();
	}

	@Step("Clicking Global Feed Link On Home Page")
	public void clickLinkGlobalFeed() {
		lnkGlobalFeed.click();
	}
	
	@Step("Validating Article Title Present on Page")
	public void validatingArticleTitlePresentOnPage(String titleToValidate, boolean editTitle) {
		if (lstArticleTitle.size() > 0) {
			boolean iflag = true;
			for(WebElement eachEle : lstArticleTitle) {
				System.out.println("Title Name"+eachEle.getText());
				if(eachEle.getText().contains(titleToValidate)) {
					Assert.assertTrue(iflag, titleToValidate+ " Article Title present on page");
					iflag = false;
					
					if(editTitle == true)
						eachEle.click();
					break;
				}
			}
			
			if(iflag == true) {
				Assert.assertFalse(iflag, titleToValidate +" Article Title is not present on page");
			}
		}
		else {
			Assert.assertFalse(true, "No Article Title present on page");
		}
	}
	
	@Step("Validating After Deleting Article Not Present on Page")
	public void validatingArticleNotPresentOnPage(String titleToValidate) {
		if (lstArticleTitle.size() > 0) {
			boolean iflag = true;
			for(WebElement eachEle : lstArticleTitle) {
				System.out.println("Title Name"+eachEle.getText());
				if(eachEle.getText().contains(titleToValidate)) {
					Assert.assertFalse(iflag, titleToValidate+ " Article present on page after deleting it");
					break;
				}
			}
			
			if(iflag == true) {
				Assert.assertTrue(iflag, titleToValidate +" Article is not present on page");
			}
		}
		else {
			Assert.assertFalse(true, "No Article Title present on page");
		}
	}
}