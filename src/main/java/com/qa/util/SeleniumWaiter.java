package com.qa.util;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class SeleniumWaiter extends TestBase {

	/**
	 *
	 * @param toFind
	 * @param timeOutTime
	 * @param caseSensitive
	 * @throws InterruptedException
	 */
	public static void guardPageContains(String toFind, String timeOutTime, boolean caseSensitive)
			throws InterruptedException {
		final long period = 1000;
		//
		boolean found = false;

		int sec = Integer.parseInt(timeOutTime);
		long timeInMs = sec * 1000;

		String pageSource = "";
		for (long remainingMs = timeInMs; remainingMs > 0; remainingMs -= period) {
			try {
				final String js = "return arguments[0].innerHTML";
				pageSource = (String) ((JavascriptExecutor) driver).executeScript(js,
						driver.findElement(By.tagName("html")));
				//
				ExpectedCondition<Boolean> expectCondition = (driver) -> ((JavascriptExecutor) driver)
						.executeScript("return document.readyState").toString().equals("complete");
				WebDriverWait wait = new WebDriverWait(driver, period);
				wait.until(expectCondition);
				//
			} catch (StaleElementReferenceException e) {
				System.out.println(e.getLocalizedMessage());
			}
			//
			if (caseSensitive) {
				found = pageSource.contains(toFind);
			} else {
				found = pageSource.toLowerCase().contains(toFind.toLowerCase());
			}
			if (found) {
				break;
			}

			Thread.sleep(1000);
		}
		//
		if (!found) {
			throw new NotFoundException("page doesn't contain " + toFind);
		}
	}

	/**
	 *
	 * @param s : HH:MM:SS:SSS , MM:SS:SSS ...
	 * @return
	 */
	static public Duration stringToDuration(String s) {
		Duration retVal = Duration.ZERO;
		String parts[] = s.split(":");
		Throwable thrownException;
		try {
			switch (parts.length) {
			case 1: {
				retVal = retVal.plus(Duration.ofMillis(Long.parseLong(s)));
				break;
			}
			case 2: {
				retVal = retVal.plus(Duration.ofMillis(Long.parseLong(parts[1])));
				retVal = retVal.plus(Duration.ofSeconds(Long.parseLong(parts[0])));
				break;
			}
			case 3: {
				retVal = retVal.plus(Duration.ofMillis(Long.parseLong(parts[2])));
				retVal = retVal.plus(Duration.ofSeconds(Long.parseLong(parts[1])));
				retVal = retVal.plus(Duration.ofMinutes(Long.parseLong(parts[0])));
				break;
			}
			case 4: {
				retVal = retVal.plus(Duration.ofMillis(Long.parseLong(parts[3])));
				retVal = retVal.plus(Duration.ofSeconds(Long.parseLong(parts[2])));
				retVal = retVal.plus(Duration.ofMinutes(Long.parseLong(parts[1])));
				retVal = retVal.plus(Duration.ofHours(Long.parseLong(parts[0])));
				break;
			}
			default: {
				thrownException = new TimeoutException("could not parse duration of [" + s + "]");
				retVal = Duration.ofMillis(250);
			}
			}
		} catch (NumberFormatException e) {
			thrownException = new TimeoutException("could not parse duration of [" + s + "]");
			retVal = Duration.ofMillis(250);
		}
		return retVal;
	}
}
