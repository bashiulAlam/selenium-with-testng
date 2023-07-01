package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {
    public CommonPage(WebDriver driver) {
        super(driver);
    }

    private final By removeBackpackFromCartButton = By.xpath("//button[@id='remove-sauce-labs-backpack']");

    public boolean isRemoveButtonVisible() {
        return getElementBy(removeBackpackFromCartButton).isDisplayed();
    }
}
