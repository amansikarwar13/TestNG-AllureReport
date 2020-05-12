package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase{
	
	@FindBy(xpath="//input[@type='email']")
	WebElement edtEmail;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement edtPassword;
	
	@FindBy(xpath="//button[text()='Sign in']")
	WebElement btnSignIn;
	
	@FindBy(xpath="//a[text()='Your Feed']")
	WebElement lnkYourFeed;
	
		
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	@Step("Getting Tilte from Login Page")
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	@Step("User Login")
	public void login(String UserName, String Password){
		edtEmail.sendKeys(UserName);
		edtPassword.sendKeys(Password);
		btnSignIn.click();
		Assert.assertTrue(lnkYourFeed.isDisplayed(), "Your Feed is not present on page after login");
	}
	
}
