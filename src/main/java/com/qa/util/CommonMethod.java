package com.qa.util;

import io.qameta.allure.Step;

public class CommonMethod {

	@Step("Validating text: {0} on page")
	public void pageContainsText(String textToFind, String timeDuration, boolean caseSensitive)
			throws InterruptedException {
		SeleniumWaiter.guardPageContains(textToFind, timeDuration, true);
	}

}
