package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	protected static Properties prop;
	private static EventFiringWebDriver e_driver;
	private static WebEventListener eventListener;
	private static String url;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/driver/chrome-windows.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/driver/firefox-windows.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		tdriver.set(driver);
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	public static void launchURL(WebDriver driver) {

		// Launching the URL:
		url = prop.getProperty("url");
		driver.get(url);
	}
}
