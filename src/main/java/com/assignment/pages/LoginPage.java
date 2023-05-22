package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
	private final By username = By.xpath("//input[@id='user-name']");
	private final By password = By.xpath("//input[@id='password']");
	private final By loginButton = By.xpath("//input[@id='login-button']");
	private final By invalidLogin = By.xpath("//div[@class='error-message-container error']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return the username
	 */
	public WebElement getUserName() {
		return getElementBy(username);
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return getElementBy(password);
	}

	/**
	 * @return the loginbtn
	 */
	public WebElement getLoginButton() {
		return getElementBy(loginButton);
	}

	public WebElement getLoginError() {
		return getElementBy(invalidLogin);
	}

	public String getLoginPageTitle() {
		return getPageTitle();
	}

	public HomePage doLogin(String username, String password) {
		getUserName().sendKeys(username);
		getPassword().sendKeys(password);
		getLoginButton().click();
		
		// Return next landing page class object
		return getInstance(HomePage.class);
	}

	public String getInvalidLoginError() {
		return getLoginError().getText();
	}

	public boolean verifyLoginPage() {
		return getUserName().isDisplayed() && getPassword().isDisplayed();
	}
}
