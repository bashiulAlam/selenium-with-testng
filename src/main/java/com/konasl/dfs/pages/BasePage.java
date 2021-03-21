package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElementBy(locator).getText();
	}

	@Override
	public WebElement getElementBy(By locator) {
		WebElement element = null;
		
		try {
			element = driver.findElement(locator);
			return element;
		}catch(Exception e) {
			System.out.println("Some Error Occured while creating element"+locator.toString());
			e.printStackTrace();
		}
		
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {
		try {
			//wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until((Function<? super WebDriver, Object>) ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {
			System.out.println("some exception occured while waiting for the element "+locator.toString());
		}
		
	}

	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until((Function<? super WebDriver, Object>) ExpectedConditions.titleContains(title));
		}catch(Exception e) {
			System.out.println("some exception occured while waiting for the title "+title);
		}
	}

}
