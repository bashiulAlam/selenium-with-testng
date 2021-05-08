package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CommonPage extends BasePage {
    By paginationCount = By.xpath("//span[@class='badge']");
    By paginationCountInModal = By.xpath("//div[@class='col-lg-12']//span[@class='badge mb-2']");
    By registerSuccessToast = By.xpath("//div[@class='toast-title ng-star-inserted']");

    By partnerCode = By.xpath("//form[@name='myForm']/div/div[2]/div[1]/div");
    By mobileAccountNo = By.xpath("//form[@name='myForm']/div/div[2]/div[2]/div");
    By merchantMobileAccountNo = By.xpath("//form[@name='myForm']/div/div/div[3]/div");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public int getPaginationCount() {
        String paginationCountStr = driver.findElement(paginationCount).getText();
        paginationCountStr = paginationCountStr.substring(paginationCountStr.lastIndexOf("f") + 1);
        paginationCountStr = paginationCountStr.substring(0, paginationCountStr.indexOf("e"));
        paginationCountStr = paginationCountStr.trim();
        return Integer.parseInt(paginationCountStr);
    }

    public int getModalPaginationCount() {
        String paginationCountStr = driver.findElement(paginationCountInModal).getText();
        paginationCountStr = paginationCountStr.substring(paginationCountStr.lastIndexOf("f") + 1);
        paginationCountStr = paginationCountStr.substring(0, paginationCountStr.indexOf("e"));
        paginationCountStr = paginationCountStr.trim();
        return Integer.parseInt(paginationCountStr);
    }

    public WebElement getTable(String tableXPath) {
        By table = By.xpath(tableXPath);
        return driver.findElement(table);
    }

    public List<WebElement> getTableRow(String tableRowXPath, String rowNum) {
        tableRowXPath = tableRowXPath.replace("rowNum", rowNum);
        By tableRow = By.xpath(tableRowXPath);
        return driver.findElements(tableRow);
    }

    public String getCellValue(String tableRowColXPath, String rowNum, String colNum) {
        tableRowColXPath = tableRowColXPath.replace("rowNum", rowNum).replace("colNum", colNum);
        By tableCellValue = By.xpath(tableRowColXPath);
        ExpectedConditions.presenceOfElementLocated(tableCellValue);
        return driver.findElement(tableCellValue).getText();
    }

    public List<WebElement> getTableCol(String tableColXPath, String colNum) {
        tableColXPath = tableColXPath.replace("colNum", colNum);
        By tableCol = By.xpath(tableColXPath);
        return driver.findElements(tableCol);
    }

    public List<String> getColumnValues(String tableColXPath, String colNum) {
        List<WebElement> columnValueElems = getTableCol(tableColXPath, colNum);

        List<String> columnValues = new ArrayList<String>();
        for (int i = 0; i < columnValueElems.size(); i++)
            columnValues.add(columnValueElems.get(i).getText());

        return columnValues;
    }

    public boolean checkSuccessToastDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(registerSuccessToast).isDisplayed();
    }

    public String getPartnerCode() {
        ExpectedConditions.presenceOfElementLocated(partnerCode);
        return driver.findElement(partnerCode).getText();
    }

    public String getMobileAccountNo() {
        return driver.findElement(mobileAccountNo).getText();
    }

    public String getMerchantMobileAccountNo() {
        return driver.findElement(merchantMobileAccountNo).getText();
    }

    public void scrollIntoView(By element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }
}
