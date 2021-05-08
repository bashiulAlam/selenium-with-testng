package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SuperDHManagementPage extends BasePage {
    public SuperDHManagementPage(WebDriver driver) {
        super(driver);
    }

    By superDhManagementMenu = By.xpath("//span[normalize-space()='Super Distributor Management']");
    By addNewSuperDh = By.xpath("//span[normalize-space()='Add New Super Distributor']");
    By searchSuperDh = By.xpath("//span[normalize-space()='Search Super Distributor']");

    public void expandSuperDhMenu() {
        CommonPage commonPage = new CommonPage(driver);
        commonPage.scrollIntoView(superDhManagementMenu);
        driver.findElement(superDhManagementMenu).click();
    }

    public void addNewSuperDh() {
        driver.findElement(addNewSuperDh).click();
    }

    public void searchSuperDh() {
        driver.findElement(searchSuperDh).click();
    }
}
