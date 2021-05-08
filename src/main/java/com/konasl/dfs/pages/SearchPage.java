package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    By accountNoInputField = By.xpath("//input[@id='accountNo']");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By detailsButton = By.xpath("//table[@class='table table-sm']//tbody//tr[1]//button[normalize-space()='Details']");

    public void setAccountNo(String accountNo) {
        driver.findElement(accountNoInputField).sendKeys(accountNo);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public boolean checkSearchButtonEnabled() {
        return driver.findElement(searchButton).isEnabled();
    }

    public void clickDetailsButton() {
        ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf(detailsButton)));
        driver.findElement(detailsButton).click();
    }
}
