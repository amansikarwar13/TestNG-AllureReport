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

	@FindBy(xpath = "//*[text()='email']")
	WebElement warningEmailCanNotBeBlank;

	@FindBy(xpath = "//*[text()='username' and text()='is too short (minimum is 1 character)']")
	WebElement warningUserNameCanNotBeBlank;

	// Initializing the Page Objects:
	public SignUpPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	@Step("Getting Tilte from Login Page")
	public String validateSignUpPageTitle() {
		return driver.getTitle();
	}

	@Step("Registering a new user using username: {0}, email: {1} and password: {2}")
	public void registerUser(String UserName, String Email, String Password) {
		edtUserName.sendKeys(UserName);
		edtEmail.sendKeys(Email);
		edtPassword.sendKeys(Password);
		btnSignIn.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		if (UserName.equals("") || UserName == "") {
			Assert.assertFalse(warningEmailCanNotBeBlank.isDisplayed(), "Email can't be empty");
			Assert.assertFalse(warningUserNameCanNotBeBlank.isDisplayed(), "username can't be empty");
		}

		if (warningEmailAlreadyExist.isDisplayed()) {
			Assert.assertFalse(warningEmailAlreadyExist.isDisplayed(), "email already exist");
			Assert.assertFalse(warningUserNameAlreadyExist.isDisplayed(), "username already exist");
		}

	}

	@Step("Registering a new user using username: {0}, email: {1} and password: {2}")
	public void registerNewUser(String UserName, String Email, String Password) {
		edtUserName.sendKeys(UserName);
		edtEmail.sendKeys(Email);
		edtPassword.sendKeys(Password);
		btnSignIn.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		String userlnk = lnkUserName.getAttribute("href");
		if (userlnk.contains(UserName)) {
			Assert.assertTrue(true, "User has registered successfully with username: " + userlnk);
		} else {
			Assert.assertFalse(false,
					"The expected username is: " + UserName + "and username showing on page is: " + userlnk);
		}
	}

}
