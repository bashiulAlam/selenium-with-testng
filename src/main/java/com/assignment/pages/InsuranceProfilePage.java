package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class InsuranceProfilePage extends BasePage {
    public InsuranceProfilePage(WebDriver driver) {
        super(driver);
    }

    By username = By.cssSelector("div[class='brgm-button brgm-user brgm-list-box'] span[class='brgm-list-title']");
    By userMenu = By.cssSelector("div[class='brgm-button brgm-user brgm-list-box'] span[class='brgm-list-title']");
    By profile = By.xpath("//a[@class='brgm-list-it'][normalize-space()='Profile']");
    By recommendSection = By.xpath("//h2[normalize-space()='I RECOMMEND']");
    By companyTitle = By.xpath("//div[@class='pr-rec-texts-container']//a");

    public String getUsername() {
        return driver.findElement(username).getText();
    }

    public void openProfile() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(userMenu)).perform();
        action.moveToElement(driver.findElement(profile)).perform();
        driver.findElement(profile).click();
    }

    public boolean isRecommendSectionDisplayed() {
        return driver.findElement(recommendSection).isDisplayed();
    }

    public String getCompanyName() {
        return driver.findElement(companyTitle).getText();
    }
}
