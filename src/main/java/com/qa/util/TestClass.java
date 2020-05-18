package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {
	
	
	public static void main(String[] args) throws IOException {
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(System.getProperty("user.dir")+"\\allurestyle\\index.html");
//		
//	
////		ClassLoader classLoader = CopyFileToDirectoryTest.class.getClassLoader();
////		Files.copy(Paths.get("D:\\Personal_Project\\TestNG-AllureReport\\allurestyle\\index.html"), Paths.get("D:\\Personal_Project\\TestNG-AllureReport\\target\\allure-report\\index.html"), StandardCopyOption.REPLACE_EXISTING);
////		
//		Path dir = Paths.get(System.getProperty("user.dir")+"\\index.html");
//		Path dirPath = Paths.get(System.getProperty("user.dir")+"\\target\\allure-report\\index.html");
//		
//		Files.copy(dir, dirPath, StandardCopyOption.REPLACE_EXISTING);
//		System.out.println(System.getProperty("user.dir")+"\\index.html");
//		System.out.println(System.getProperty("user.dir")+"\\target\\allure-report\\index.html");
	
		int i = 2;
		int j = 4;
		int k = 1;
		int x = 2;
		
		if(i == x) {
			Assert.assertTrue(true, "i and x value are not equal");
		}
		
		if(i == j) {
			Assert.assertTrue(true, "i and j value are not equal");
		}
	
	
	}
	
//	@Test
	public void test() {
		int i = 2;
		int j = 4;
		int k = 1;
		int x = 2;
		
		if(i == x) {
			Assert.assertFalse(true, "i and k value are not equal");
		}
		
		if(i != j) {
			Assert.assertFalse(false, "i and j value are not equal");
		}
	}
}
