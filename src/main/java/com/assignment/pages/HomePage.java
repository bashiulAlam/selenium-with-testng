package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{
	private final By hamburgerIcon = By.xpath("//button[@id='react-burger-menu-btn']");
	private final By logoutOption = By.xpath("//a[@id='logout_sidebar_link']");

	public WebElement getHamburgerIcon() {
		return getElementBy(hamburgerIcon);
	}

	public WebElement getLogoutOption() {
		return getElementBy(logoutOption);
	}

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getHomePageTitle() {
		return getPageTitle();
	}

	public void doLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(getHamburgerIcon()));
		getHamburgerIcon().click();
		getLogoutOption().click();
	}
}
