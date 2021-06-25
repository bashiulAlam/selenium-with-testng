package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InsuranceLoginPage extends BasePage {
    public InsuranceLoginPage(WebDriver driver) {
        super(driver);
    }

    By loginLink = By.xpath("//span[normalize-space()='Login']");
    By emailField = By.xpath("//input[@id='email']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//button[@class='btn blue center reg-tabs-bt touch-element-cl']");

    public void openLoginPage() {
        driver.findElement(loginLink).click();
    }

    public void typeEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    public void typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public void login(String userEmail, String password) {
        typeEmail(userEmail);
        typePassword(password);
        clickLoginBtn();
    }
}
