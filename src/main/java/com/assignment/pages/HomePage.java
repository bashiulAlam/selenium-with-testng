package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
	private final By hamburgerIcon = By.xpath("//button[@id='react-burger-menu-btn']");
	private final By logoutOption = By.xpath("//a[@id='logout_sidebar_link']");
	private final By addBackpackToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	private final By cartButton = By.xpath("//a[@class='shopping_cart_link']");
	private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");

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

	public void addBackpackToCart() {
		getElementBy(addBackpackToCartButton).click();
	}

	public boolean isCartBadgeDisplayed() {
		return getElementBy(cartBadge).isDisplayed();
	}

	public String getCartItemNumber() {
		return getElementBy(cartBadge).getText();
	}

	public void openCart() {
		getElementBy(cartButton).click();
	}

	public void doLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(getHamburgerIcon()));
		getHamburgerIcon().click();
		getLogoutOption().click();
	}
}
