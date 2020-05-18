package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Test {
	
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir")+"\\allurestyle\\index.html");
		
	
//		ClassLoader classLoader = CopyFileToDirectoryTest.class.getClassLoader();
//		Files.copy(Paths.get("D:\\Personal_Project\\TestNG-AllureReport\\allurestyle\\index.html"), Paths.get("D:\\Personal_Project\\TestNG-AllureReport\\target\\allure-report\\index.html"), StandardCopyOption.REPLACE_EXISTING);
//		
		Path dir = Paths.get(System.getProperty("user.dir")+"\\index.html");
		Path dirPath = Paths.get(System.getProperty("user.dir")+"\\target\\allure-report\\index.html");
		
		Files.copy(dir, dirPath, StandardCopyOption.REPLACE_EXISTING);
//		System.out.println(System.getProperty("user.dir")+"\\index.html");
//		System.out.println(System.getProperty("user.dir")+"\\target\\allure-report\\index.html");
	}
}
