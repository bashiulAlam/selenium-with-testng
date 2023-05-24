package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
	private final By username = By.xpath("//input[@id='user-name']");
	private final By password = By.xpath("//input[@id='password']");
	private final By loginButton = By.xpath("//input[@id='login-button']");
	private final By invalidLogin = By.xpath("//div[@class='error-message-container error']");
	private final By dismissErrorMessage = By.xpath("//button[@class='error-button']//*[name()='svg']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getUserName() {
		return getElementBy(username);
	}

	public WebElement getPassword() {
		return getElementBy(password);
	}

	public WebElement getLoginButton() {
		return getElementBy(loginButton);
	}

	public WebElement getLoginError() {
		return getElementBy(invalidLogin);
	}

	public WebElement getDismissErrorMessageButton() {
		return getElementBy(dismissErrorMessage);
	}

	public String getLoginPageTitle() {
		return getPageTitle();
	}

	public HomePage doLogin(String username, String password) {
		refreshPage();
		getUserName().sendKeys(username);
		getPassword().sendKeys(password);

		getLoginButton().click();
		
		// Return next landing page class object
		return getInstance(HomePage.class);
	}

	public String getInvalidLoginError() {
		String errorMessage = getLoginError().getText();
		getDismissErrorMessageButton().click();
		return errorMessage;
	}

	public boolean verifyLoginPage() {
		return getUserName().isDisplayed() && getPassword().isDisplayed();
	}

	public void refreshPage() {
		driver.navigate().refresh();

	}
}
