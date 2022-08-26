package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PoiPage extends BasePage {
    public PoiPage(WebDriver driver) {
        super(driver);
    }

    By locationField = By.xpath("//input[@id='mat-input-0']");
    By searchOption1 = By.xpath("//mat-option[@id='mat-option-180']");
    By searchOption2 = By.xpath("//mat-option[@id='mat-option-181']");
    By embedMapButton = By.xpath("//button[@class='mat-stroked-button mat-button-base']");
    By embedPupUp = By.xpath("//mat-dialog-container[@id='mat-dialog-4']");
    By popUpContent = By.xpath("//div[@class='iframe-code']");
    By popUpCloseButton = By.xpath("//span[normalize-space()='Close']");
    By suggestionContainer = By.xpath("//div[@class='suggestion-container']");
    By carAndVehicleGroup = By.xpath("//span[normalize-space()='Cars-vehicles']");

    public String getPoiPageTitle() {
        return getPageTitle();
    }

    public String getEmbedPopUpContent() {
        driver.findElement(embedMapButton).click();
        return driver.findElement(popUpContent).getText();
    }

    public void closeEmbedPopUp() {
        driver.findElement(popUpCloseButton).click();
    }

    public void sendLocationText(String location) {
        driver.findElement(locationField).sendKeys(location);
    }

    public int locationOptionsDisplayed() {
        return driver.findElements(suggestionContainer).size();
    }

    public void clickSearchOption() {
        List<WebElement> suggestionList = driver.findElements(suggestionContainer);
        System.out.println("list size : " + suggestionList.size());
        suggestionList.get(0).click();
    }

    public void clickOnVehicleCategory() {
        driver.findElement(carAndVehicleGroup).click();
    }
}
