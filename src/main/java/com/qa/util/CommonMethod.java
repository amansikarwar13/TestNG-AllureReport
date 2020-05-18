package com.qa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.testng.annotations.Test;

import io.qameta.allure.Step;

public class CommonMethod {

	@Step("Validating text: {0} on page")
	public static void pageContainsText(String textToFind, String timeDuration, boolean caseSensitive)
			throws InterruptedException {
		SeleniumWaiter.guardPageContains(textToFind, timeDuration, true);
	}

	public String generateRandomString() {
		SimpleDateFormat zuluTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss", Locale.US);
		Date now = new Date();
		return zuluTimeFormat.format(now).replaceAll("-","");
	}

}
