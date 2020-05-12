package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

import io.qameta.allure.Step;

public class SignUpPage extends TestBase {

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement edtUserName;

	@FindBy(xpath = "//input[@type='email']")
	WebElement edtEmail;

	@FindBy(xpath = "//input[@type='password']")
	WebElement edtPassword;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	WebElement btnSignIn;
	
	@FindBy(xpath = "//a[img[@class='user-pic']]")
	WebElement lnkUserName;
	
	@FindBy(xpath = "//*[text()='email' and text()='has already been taken']")
	WebElement warningEmailAlreadyExist;
	
	@FindBy(xpath = "//*[text()='username' and text()='has already been taken']")
	WebElement warningUserNameAlreadyExist;
	
	// Initializing the Page Objects:
	public SignUpPage(){
		PageFactory.initElements(driver, this);
	}

	// Actions:
	@Step("Getting Tilte from Login Page")
	public String validateSignUpPageTitle() {
		return driver.getTitle();
	}

	@Step("Registering a new user")
	public void registerUser(String UserName, String Email, String Password) {
		edtUserName.sendKeys(UserName);
		edtEmail.sendKeys(Email);
		edtPassword.sendKeys(Password);
		btnSignIn.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		Assert.assertFalse(warningEmailAlreadyExist.isDisplayed(),"email already exist");
		Assert.assertFalse(warningUserNameAlreadyExist.isDisplayed(),"username already exist");
		
		String userlnk = lnkUserName.getAttribute("href");
		Assert.assertEquals(UserName, userlnk,"The expected username is: "+UserName+"and username showing on page is: "+userlnk);
	}

}
