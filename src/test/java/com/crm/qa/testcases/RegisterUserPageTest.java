package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ExtentReportListener.ExtentReporterNG;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.SignUpPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class RegisterUserPageTest extends TestBase{
	SignUpPage signUp;
	HomePage homePage;
	ExtentReporterNG erng;
	
	public RegisterUserPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		signUp = new SignUpPage();	
		homePage = new HomePage();
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verify User Registration")
	@Story("Story Name: Verify New Registration")
	@Test(priority=1)
	public void createUser(){
		homePage.verifyLinkSignUp();
		homePage.clickLinkSignUp();
		signUp.registerUser("TestData@gmail.com", "TestDataRunTime@gmail.com", "Password!23");
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
