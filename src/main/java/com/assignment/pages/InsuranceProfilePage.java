package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class InsuranceProfilePage extends BasePage {
    public InsuranceProfilePage(WebDriver driver) {
        super(driver);
    }

    By userMenu = By.cssSelector("div[class='brgm-button brgm-user brgm-list-box'] span[class='brgm-list-title']");
    By profile = By.xpath("//a[@class='brgm-list-it'][normalize-space()='Profile']");

    public void openProfile() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(userMenu)).perform();
        driver.findElement(profile).click();
    }
}
