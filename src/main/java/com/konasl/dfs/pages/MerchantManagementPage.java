package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MerchantManagementPage extends BasePage {
    public MerchantManagementPage(WebDriver driver) {
        super(driver);
    }

    By merchantManagementMenu = By.xpath("//span[normalize-space()='Merchant Management']");
    By addNewMerchant = By.xpath("//span[normalize-space()='Merchant Registration']");
    By searchMerchant = By.xpath("//span[normalize-space()='Search Merchant']");

    public void expandMerchantManagementMenu() {
        CommonPage commonPage = new CommonPage(driver);
        commonPage.scrollIntoView(merchantManagementMenu);
        driver.findElement(merchantManagementMenu).click();
    }

    public void openAddNewMerchant() {
        driver.findElement(addNewMerchant).click();
    }

    public void openSearchMerchant() {
        driver.findElement(searchMerchant).click();
    }
}
