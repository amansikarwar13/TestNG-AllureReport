package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Verifying Title For Home Page")
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	@Step("Getting Home Link On Home Page")
	public boolean verifyLinkHome(){
		return lnkHome.isDisplayed() && lnkHome.isEnabled();
	}
	
	@Step("Verifying Sign In Link On Home Page")
	public boolean verifyLinkSignIn(){
		return lnkSignIn.isDisplayed() && lnkSignIn.isEnabled();
	}
	
	@Step("Clicking Sign In Link On Home Page")
	public void clickLinkSignIn(){
		lnkSignIn.click();
	}
	
	@Step("Clicking Sign Up Link On Home Page")
	public void clickLinkSignUp(){
		lnkSignUp.click();
	}
	
	@Step("Verifying Sign Up Link On Home Page")
	public boolean verifyLinkSignUp(){
		return lnkSignUp.isDisplayed() && lnkSignUp.isEnabled();
	}
	
	@Step("Returning List Of All Popular Tags Present On Home Page")
	public List<WebElement> returnListOfPopularTags(){
		return lstOfTags;
	}
}
	
//	public void clickOnNewContactLink(){
//		Actions action = new Actions(driver);
//		action.moveToElement(contactsLink).build().perform();
//		newContactLink.click();
//		
//	}
	