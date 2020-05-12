package com.qa.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseClassFinder;

import com.qa.base.TestBase;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener{

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart Method: "+getTestMethodName(result) + " start");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess Method: "+getTestMethodName(result) + " success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("I am in onTestFailure Method: "+ getTestMethodName(result) + " failed");
		
		Object testClass = result.getInstance();
		WebDriver driver = TestBase.getDriver();
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot captured for testcase: "+getTestMethodName(result));
			saveScreenShot(driver);
		}
		saveTextLog(getTestMethodName(result) +" failed and screenshot taken");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped Method: "+result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("I am in onStart Method: "+context.getName());
		context.setAttribute("WebDriver", TestBase.getDriver());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish Method: "+context.getName());
		
	}
}
