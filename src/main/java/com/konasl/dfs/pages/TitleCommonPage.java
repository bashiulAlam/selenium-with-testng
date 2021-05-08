package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TitleCommonPage extends BasePage {
    public TitleCommonPage(WebDriver driver) {
        super(driver);
    }

    By detailsPageHeader = By.xpath("//h4[@class='card-title']");
    By merchantDetailsPageHeader = By.xpath("//div[@class='content-header']");

    public String getDetailsPageHeader() {
        return driver.findElement(detailsPageHeader).getText();
    }

    public String getMerchantDetailsPageHeader() {
        return driver.findElement(merchantDetailsPageHeader).getText();
    }
}
