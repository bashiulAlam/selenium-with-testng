package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By itemName = By.xpath("//div[@class='inventory_item_name']");
    private final By checkoutButton = By.xpath("//button[@id='checkout']");
    private final By firstName = By.xpath("//input[@id='first-name']");
    private final By lastName = By.xpath("//input[@id='last-name']");
    private final By postCode = By.xpath("//input[@id='postal-code']");
    private final By continueButton = By.xpath("//input[@id='continue']");
    private final By totalAmount = By.xpath("//div[@class='summary_info_label summary_total_label']");
    private final By finishButton = By.xpath("//button[@id='finish']");
    private final By checkoutSuccessMessage = By.xpath("//h2[@class='complete-header']");

    public String getItemName() {
        return getElementBy(itemName).getText();
    }

    public void checkout() {
        getElementBy(checkoutButton).click();
    }

    public void submitCheckoutInformation(String firstNameInput, String lastNameInput, String postCodeInput) {
        getElementBy(firstName).sendKeys(firstNameInput);
        getElementBy(lastName).sendKeys(lastNameInput);
        getElementBy(postCode).sendKeys(postCodeInput);
        getElementBy(continueButton).click();
    }

    public boolean isTotalAmountVisible() {
        return getElementBy(totalAmount).isDisplayed();
    }

    public void finishCheckout() {
        getElementBy(finishButton).click();
    }

    public String getSuccessCheckoutMessage() {
        return getElementBy(checkoutSuccessMessage).getText();
    }
}
