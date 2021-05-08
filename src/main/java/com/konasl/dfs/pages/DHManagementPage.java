package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DHManagementPage extends BasePage {
    public DHManagementPage(WebDriver driver) {
        super(driver);
    }

    By dhManagementMenu = By.xpath("//span[normalize-space()='Distributor Management']");
    By addNewDh = By.xpath("//span[normalize-space()='Add New Distributor']");
    By searchDh = By.xpath("//span[normalize-space()='Search Distributor']");

    public void expandDhManagementMenu() {
        driver.findElement(dhManagementMenu).click();
    }

    public void openAddNewDh() {
        driver.findElement(addNewDh).click();
    }

    public void openSearchDh() {
        driver.findElement(searchDh).click();
    }
}
