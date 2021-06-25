package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage {
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    By searchBar = By.xpath("//input[@name='q']");
    By searchBtn = By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']");
    By resultStat = By.xpath("//div[@id='result-stats']");

    public void setSearchParam(String searchString) {
        driver.findElement(searchBar).sendKeys(searchString);
    }

    public void clickSearchBtn() {
        driver.findElement(searchBtn).click();
    }

    public void search(String searchString) {
        setSearchParam(searchString);
        clickSearchBtn();
    }

    public long resultCount() {
        String count = driver.findElement(resultStat).getText();
        count = count.substring(count.indexOf('t') + 1, count.indexOf('r'));
        count = count.replace(",", "");
        count = count.trim();

        return Long.parseLong(count);
    }
}
