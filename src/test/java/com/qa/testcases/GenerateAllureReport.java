package com.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;

public class GenerateAllureReport {

	@AfterSuite
	private void generateAllureReports() throws InterruptedException {
		try {
			System.out.println("Inside After suite");
			// Creating History Folder inside allure-results
			File dir = new File(System.getProperty("user.dir") + "\\target\\allure-results\\history");
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			// To generate the history use --clean
			Runtime.getRuntime().exec("cmd.exe /c npx allure generate allure-results --clean", null,
					new File(System.getProperty("user.dir") + "\\target"));
			Thread.sleep(5000);

			Path fromCat = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-report\\history\\categories-trend.json");
			Path fromDur = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-report\\history\\duration-trend.json");
			Path fromHistory = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-report\\history\\history.json");
			Path fromHisTrend = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-report\\history\\history-trend.json");
			Path fromRetTrend = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-report\\history\\retry-trend.json");

			Path ToCat = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-results\\history\\categories-trend.json");
			Path ToDur = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-results\\history\\duration-trend.json");
			Path ToHistory = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-results\\history\\history.json");
			Path ToHisTrend = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-results\\history\\history-trend.json");
			Path ToRetTrend = Paths
					.get(System.getProperty("user.dir") + "\\target\\allure-results\\history\\retry-trend.json");

			Files.copy(fromCat, ToCat, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(fromDur, ToDur, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(fromHistory, ToHistory, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(fromHisTrend, ToHisTrend, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(fromRetTrend, ToRetTrend, StandardCopyOption.REPLACE_EXISTING);

//			Runtime.getRuntime().exec("cmd.exe /c npx allure generate allure-results", null, new File(System.getProperty("user.dir")+"\\target"));

			File fromDir = new File(System.getProperty("user.dir") + "\\allurestyle\\index.html");
			File toDir = new File(System.getProperty("user.dir") + "\\target\\allure-report\\index.html");
			FileUtils.copyFile(fromDir, toDir);

			Thread.sleep(2000);
			Runtime.getRuntime().exec("cmd.exe /c npx allure open", null,
					new File(System.getProperty("user.dir") + "\\target"));
		} catch (IOException e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
	}

}
